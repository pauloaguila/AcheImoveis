/*
 * Copyright (c) 2013 Samsung Electronics Co., Ltd.
 *
 * All rights reserved.
 *
 * This software is a confidential and proprietary information of Samsung
 * Electronics, Inc. ("Confidential Information").  You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Samsung Electronics.
 */

//Author - Marcelo Mazzini - 51130257

package com.trabalho.acheimoveis.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import com.trabalho.acheimoveis.utils.Constants;
import com.trabalho.acheimoveis.utils.MemoryHelper;

import android.content.Context;
import android.util.Log;

public class SplashController {

    private boolean isValid;
    private static SplashController aplicationController = null;
    private final Context applicationContext;

    public static SplashController getInstance(Context context) {
        SplashController.aplicationController = new SplashController(
            context);

        return SplashController.aplicationController;
    }

    private SplashController(Context context) {
        this.applicationContext = context;
        init();
    }

    private void init() {

        // Check for memory availability
        if (MemoryHelper.hasSpaceAvailable(this.applicationContext)) {

            this.isValid = importDatabase(this.applicationContext,
                Constants.DATABASE_NAME);
        }

        else {
            this.isValid = false;
        }

    }

    public boolean isValid() {
        return this.isValid;
    }

    public static boolean importDatabase(Context context, String databaseName) {

        File databasePath = context.getDatabasePath(databaseName);

        // There is a new version of database, delete the old one
        if (Constants.DATABASE_OLD_VERSION < Constants.DATABASE_NEW_VERSION) {

            databasePath.delete();
        }

        databasePath = databasePath.getParentFile();

        // List all databases in application area
        String databases[] = databasePath.list(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String filename) {
                return filename.endsWith(Constants.DATABASE_EXTENTION);
            }
        });

        if (databases == null || databases.length == 0) {
            // Copy database file from assets to the database folder
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            boolean result = true;
            try {

                // Creates "databases" directory in the first access or ignores
                // if already exist
                databasePath.mkdirs();

                inputStream = context.getAssets().open(databaseName);
                fileOutputStream = new FileOutputStream(
                    context.getDatabasePath(databaseName));

                byte[] buffer = new byte[1024];
                int read;
                while((read = inputStream.read(buffer)) > 0)
                    fileOutputStream.write(buffer, 0, read);
                fileOutputStream.close();
            } catch(IOException e) {
                e.printStackTrace();
                result = false;
                Log.i("file - ", "MOBIBLE: splash" + "File copy error");
            } finally {
                try {
                    fileOutputStream.close();
                    inputStream.close();
                } catch(Exception e) {
                    e.printStackTrace();
                    result = false;
                    Log.i("file - ", "MOBIBLE: splash" + "Could not close databae file");
                }
            }

            return result;
        }

        // Avoid database being copied again in case it already exists on user
        // data area
        else {
            return true;
        }
    }

}
