package org.wikipedia.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// TODO: Replace with Apache Commons Lang StringUtils.
public final class StringUtil {
    private static final String CSV_DELIMITER = ",";

    @NonNull
    public static String emptyIfNull(@Nullable String value) {
        return defaultIfNull(value, "");
    }

    @NonNull
    public static CharSequence emptyIfNull(@Nullable CharSequence value) {
        return defaultIfNull(value, "");
    }

    @Nullable
    public static String defaultIfNull(@Nullable String value, @Nullable String defaultValue) {
        return value == null ? defaultValue : value;
    }

    @Nullable
    public static CharSequence defaultIfNull(@Nullable CharSequence value,
            @Nullable CharSequence defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static boolean equals(CharSequence lhs, CharSequence rhs) {
        return emptyIfNull(lhs).equals(emptyIfNull(rhs));
    }

    @NonNull
    public static String listToCsv(@NonNull List<String> list) {
        return listToDelimitedString(list, CSV_DELIMITER);
    }

    @NonNull
    public static String listToDelimitedString(@NonNull Iterable<String> list,
                                               @NonNull String delimiter) {
        return TextUtils.join(delimiter, list);
    }

    /** @return Nonnull immutable list. */
    @NonNull
    public static List<String> csvToList(@NonNull String csv) {
        return delimiterStringToList(csv, CSV_DELIMITER);
    }

    /** @return Nonnull immutable list. */
    @NonNull
    public static List<String> delimiterStringToList(@NonNull String delimitedString,
                                                     @NonNull String delimiter) {
        return Arrays.asList(TextUtils.split(delimitedString, delimiter));
    }

    @NonNull
    public static String[] removeNulls(@NonNull String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(args));
        list.removeAll(Collections.singleton(null));
        return list.toArray(new String[list.size()]);
    }

    /**
     * Compares two strings properly, even when one of them is null - without throwing up
     *
     * @param str1 The first string
     * @param str2 Guess?
     * @return true if they are both equal (even if both are null)
     */
    public static boolean compareStrings(@Nullable String str1, @Nullable String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

    /**
     * Capitalise the first character of the description, for style
     *
     * @param orig original string
     * @return same string as orig, except the first letter is capitalized
     */
    public static String capitalizeFirstChar(@NonNull String orig) {
        return orig.substring(0, 1).toUpperCase() + orig.substring(1);
    }

    /**
     * Creates an MD5 hash of the provided string and returns its ASCII representation
     * @param s String to hash
     * @return ASCII MD5 representation of the string passed in
     */
    public static String md5string(String s) {
        StringBuilder hexStr = new StringBuilder();
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes("utf-8"));
            byte[] messageDigest = digest.digest();

            final int maxByteVal = 0xFF;
            for (byte b : messageDigest) {
                hexStr.append(Integer.toHexString(maxByteVal & b));
            }
        } catch (NoSuchAlgorithmException e) {
            // This will never happen, yes.
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            // This will never happen, yes.
            throw new RuntimeException(e);
        }
        return hexStr.toString();
    }

    /**
     * Remove leading and trailing whitespace from a CharSequence. This is useful after using
     * the fromHtml() function to convert HTML to a CharSequence.
     * @param str CharSequence to be trimmed.
     * @return The trimmed CharSequence.
     */
    public static CharSequence trim(CharSequence str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int len = str.length();
        int start = 0;
        int end = len - 1;
        while (Character.isWhitespace(str.charAt(start)) && start < len) {
            start++;
        }
        while (Character.isWhitespace(str.charAt(end)) && end > 0) {
            end--;
        }
        if (end > start) {
            return str.subSequence(start, end + 1);
        }
        return "";
    }

    private StringUtil() { }
}