package com.dzaitsev.c360photorenamer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by dmitriyzaitsev on 8/16/15.
 */
public class PhotoC360Test {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPhoto360() throws IOException {
        final String userDir = System.getProperty("user.dir");
        final String name = "C360_2015-07-25-07-40-28-323_org.jpg";
        final File photo = new File(userDir, name);
        photo.createNewFile();
        assertThat(photo.exists());
        final PhotoC360 photoC360 = new PhotoC360(name);
        final File renamedFile = new File(userDir, photoC360.formatName());
        photo.renameTo(renamedFile);
        assertThat(renamedFile.exists());
        assertThat(!photo.exists());
        assertThat(photoC360.formatName()).isEqualTo(renamedFile.getName());
        renamedFile.delete();
        assertThat(!renamedFile.exists());
    }

    @After
    public void tearDown() throws Exception {
    }
}
