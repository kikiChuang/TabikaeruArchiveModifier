package com.aa65535.tabikaeruarchivemodifier.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class Util {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }

    public static int readInt(File archive, int offset) {
        RandomAccessFile r = null;
        try {
            r = new RandomAccessFile(archive, "r");
            r.seek(offset);
            return r.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(r);
        }
        return -1;
    }

    public static boolean writeInt(File archive, int offset, int value) {
        RandomAccessFile r = null;
        try {
            r = new RandomAccessFile(archive, "rwd");
            r.seek(offset);
            r.writeInt(value);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(r);
        }
        return false;
    }

    public static Calendar readCalendar(File archive, int offset) {
        RandomAccessFile r = null;
        Calendar calendar = Calendar.getInstance();
        try {
            r = new RandomAccessFile(archive, "r");
            r.seek(offset);
            calendar.set(r.readInt(), r.readInt() - 1, r.readInt(), r.readInt(), r.readInt(), r.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(r);
        }
        return calendar;
    }

    public static boolean writeCalendar(File archive, int offset, Calendar value) {
        RandomAccessFile r = null;
        try {
            r = new RandomAccessFile(archive, "rwd");
            r.seek(offset);
            r.writeInt(value.get(Calendar.YEAR));
            r.writeInt(value.get(Calendar.MONTH) + 1);
            r.writeInt(value.get(Calendar.DAY_OF_MONTH));
            r.writeInt(value.get(Calendar.HOUR_OF_DAY));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(r);
        }
        return false;
    }
}
