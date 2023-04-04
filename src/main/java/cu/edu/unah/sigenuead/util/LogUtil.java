package cu.edu.unah.sigenuead.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author Nerzur
 * @param <T> Class used.
 */
public class LogUtil<T> {

    private String username;
    private String ip;
    private T classUsed;
    private final Logger log = LoggerFactory.getLogger(classUsed.getClass());

    public LogUtil(T classUsed) {
        this.classUsed = classUsed;
        obtain_data();
    }

    private void obtain_data() {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            ip = InetAddress.getLocalHost().getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            ip = "127.0.0.1";
        }
    }

    private void writeLog(int logSeverity, String message) {
        Date time = new Date();
        switch (logSeverity) {
            case 0 -> log.info(time.toGMTString() + " -- " + username + " -- " + ip + " -- " + message);
            case 1 -> log.warn(time.toGMTString() + " -- " + username + " -- " + ip + " -- " + message);
            case 2 -> log.error(time.toGMTString() + " -- " + username + " -- " + ip + " -- " + message);
        }
    }

    public void writeInfoLog(String message) {
        writeLog(0, message);
    }

    public void writeWarnLog(String message) {
        writeLog(1, message);
    }

    public void writeErrorLog(String message) {
        writeLog(2, message);
    }
}
