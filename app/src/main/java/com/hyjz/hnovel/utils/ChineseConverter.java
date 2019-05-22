package com.hyjz.hnovel.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChineseConverter {
    public ChineseConverter() {
    }

    public static String convert(String text, ConversionType conversionType, Context context) {
        File lastDataFile = new File(context.getFilesDir() + "/openccdata/zFinished");
        if (!lastDataFile.exists()) {
            initialize(context);
        }

        File dataFolder = new File(context.getFilesDir() + "/openccdata");
        return convert(text, conversionType.getValue(), dataFolder.getAbsolutePath());
    }

    public static void clearDictDataFolder(Context context) {
        File dataFolder = new File(context.getFilesDir() + "/openccdata");
        deleteRecursive(dataFolder);
    }

    private static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] var1 = fileOrDirectory.listFiles();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                File child = var1[var3];
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }

    private static native String convert(String var0, String var1, String var2);

    private static void initialize(Context context) {
        copyFolder("openccdata", context);
    }

    private static void copyFolder(String folderName, Context context) {
        File fileFolderOnDisk = new File(context.getFilesDir() + "/" + folderName);
        if (!fileFolderOnDisk.exists()) {
            AssetManager assetManager = context.getAssets();
            String[] files = null;

            try {
                files = assetManager.list(folderName);
            } catch (IOException var26) {
                Log.e("tag", "Failed to get asset file list.", var26);
            }

            if (files != null) {
                String[] var5 = files;
                int var6 = files.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String filename = var5[var7];
                    InputStream in = null;
                    FileOutputStream out = null;

                    try {
                        in = assetManager.open(folderName + "/" + filename);
                        if (!fileFolderOnDisk.exists()) {
                            fileFolderOnDisk.mkdirs();
                        }

                        File outFile = new File(fileFolderOnDisk.getAbsolutePath(), filename);
                        if (!outFile.exists()) {
                            outFile.createNewFile();
                        }

                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                    } catch (IOException var25) {
                        Log.e("tag", "Failed to copy asset file: " + filename, var25);
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException var24) {
                                ;
                            }
                        }

                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException var23) {
                                ;
                            }
                        }

                    }
                }
            }

        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];

        int read;
        while((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }

    }

    static {
        System.loadLibrary("c++_shared");
        System.loadLibrary("lib-opencc-android");
    }
}
