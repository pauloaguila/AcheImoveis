package com.trabalho.acheimoveis.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Environment;
import android.os.StatFs;

public class MemoryHelper {

    private static HashMap<String, Long> databasesSize = new HashMap<String, Long>();
    private static long allMemoryAvailable = 0;
    private static long allMemoryNeeded = 0;

    public static boolean hasSpaceAvailable(Context applicationContext) {

        // Get the application database parent path
        File databasePath = applicationContext.getDatabasePath(Constants.DATABASE_NAME);
        databasePath = databasePath.getParentFile();

        // List all databases in application area if any
        String databases[] = databasePath.list(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String filename) {
                return filename.endsWith(Constants.DATABASE_EXTENTION);
            }
        });

        // Creates and populates the assets list with all assests found on
        // application
        String assetsContent[] = {};
        try {
            assetsContent = applicationContext.getAssets().list("");
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Save databases size to avoid calculate it again next time
        for(int i = 0; i < assetsContent.length; ++i) {
            if (!assetsContent[i].endsWith(Constants.DATABASE_EXTENTION))
                continue;

            if (!MemoryHelper.databasesSize.containsKey(assetsContent[i])) {
                long assetDBLength = MemoryHelper.getDatabaseSize(applicationContext, null, assetsContent[i]);
                MemoryHelper.databasesSize.put(assetsContent[i], assetDBLength);
            }
        }

        // Database was not copied to user area look for its size in Assets
        // folder
        if (databases == null || databases.length == 0) {

            allMemoryAvailable = MemoryHelper.getAvailableMemory();
            allMemoryNeeded = MemoryHelper.databasesSize.get(Constants.DATABASE_NAME);
            return allMemoryAvailable > allMemoryNeeded;

        }

        // Database already exists in user data
        else {

            if (Constants.DATABASE_OLD_VERSION < Constants.DATABASE_NEW_VERSION) {

                // Get the size of the database from Assets folder
                allMemoryNeeded = MemoryHelper.databasesSize.get(Constants.DATABASE_NAME);

                // Get the size of the database from user data area
                long currentDatabaseSize = MemoryHelper.getDatabaseSize(applicationContext, databasePath,
                    Constants.DATABASE_NAME);

                allMemoryAvailable = MemoryHelper.getAvailableMemory() + currentDatabaseSize;

                // Return the status of memory availability - True = available
                // || False = not available
                return allMemoryAvailable > allMemoryNeeded;

            }
            // Avoid to execute memory check in case database already exists on
            // user data area
            else {
                return true;
            }
        }
    }

    public static boolean hasSpaceAvailableForDatabase(Context applicationContext, String databasePath, String database) {
        long databaseSize = getDatabaseSize(applicationContext, new File(databasePath), database);
        return MemoryHelper.getAvailableMemory() > databaseSize;
    }

    private static long getDatabaseSize(Context applicationContext, File databasePath, String database) {

        long size = 0;

        // Database is already in user area
        if (databasePath != null) {
            String filePath = databasePath + "/" + database;
            size = new File(filePath).length();
        } else {
            // Database is only in assets folder
            try {
                AssetFileDescriptor descriptor = applicationContext.getAssets().openFd(database);
                size = descriptor.getLength();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        // Return the database size according to which one was found
        return size;
    }

    public static long getAvailableMemory() {
        // Calculate available memory in application storage location
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public static long getTotalMemoryAvailable() {
        return MemoryHelper.allMemoryAvailable;
    }

    public static long getTotalMemoryNeeded() {
        return MemoryHelper.allMemoryNeeded;
    }

}
