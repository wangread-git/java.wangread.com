package com.read.test.rocksdb;

import org.junit.Test;
import org.rocksdb.*;

public class TestRocksDB {

    private final static String PATH = "/Users/yfwangrui/export/Data/rocksdb";

    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) {
        (new TestRocksDB()).write();
    }

    @Test
    public void write() {
        try (final Options options = new Options().setCreateIfMissing(true)) {
            try (final RocksDB db = RocksDB.open(options, PATH)) {
                long start = System.currentTimeMillis();
                for (int i = 0; i < 1000000; i++) {
                    db.put(String.valueOf(i).getBytes(), (i + " value store to rocks db").getBytes());
                }
                System.out.println("write cost: " + (System.currentTimeMillis() - start));

                start = System.currentTimeMillis();

                RocksIterator it = db.newIterator();
                for (it.seekToFirst(); it.isValid(); it.next()) {
                    byte[] bytes = it.value();
                    String value = new String(bytes);
//                    System.out.println(value);
                }
//                for (int i = 0; i < 1000000; i++) {
//                    byte[] bytes = db.get(String.valueOf(i).getBytes());
//                    String value = new String(bytes);
////                    System.out.println(value);
//                }
                System.out.println("read cost: " + (System.currentTimeMillis() - start));
            }
        } catch (RocksDBException e) {
            throw new RuntimeException(e);
        }
    }
}
