package com.tw.net.utils;

import android.content.Context;

import com.tw.utils.base.ArrayUtil;
import com.tw.utils.base.ObjectUtil;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by wei.tian
 * 2017/9/18
 */

public final class HttpsUtil {
    private HttpsUtil() {
        throw new IllegalStateException("No instance!");
    }

    public static Object[] getSSLSocketFactory(Context context, int[] certificates) {
        ObjectUtil.checkNonNull(context, "context == null");
        ArrayUtil.checkNotEmpty(certificates, "certificates are empty");
        try {
            Object[] sslSocketFactory = new Object[2];
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    certificate.close();
                }
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            sslSocketFactory[0] = sslContext.getSocketFactory();
            sslSocketFactory[1] = trustManager;
            return sslSocketFactory;
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HostnameVerifier getHostnameVerifier(final String[] hosts) {
        ArrayUtil.checkNotEmpty(hosts, "hosts are empty");
        HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                for (String host : hosts) {
                    if (host.equalsIgnoreCase(hostname)) {
                        return true;
                    }
                }
                return false;
            }
        };
        return verifier;
    }
}
