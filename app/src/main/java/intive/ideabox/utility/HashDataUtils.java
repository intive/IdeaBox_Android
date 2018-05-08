package intive.ideabox.utility;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Created by Pc on 06.05.2018.
 */

public class HashDataUtils {
    public static String getHashedData(String data) {
        return Hashing.sha256()
                .hashString(data, StandardCharsets.UTF_8)
                .toString();

    }
}
