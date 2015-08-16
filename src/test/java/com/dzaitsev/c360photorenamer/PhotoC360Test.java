package com.dzaitsev.c360photorenamer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by dmitriyzaitsev on 8/16/15.
 */
public class PhotoC360Test {

    private static final String NAME_OLD = "C360_2015-07-25-07-40-28-323.jpg";

    @Test
    public void testFormat() {
        final String newName = PhotoC360.format(NAME_OLD);
        final String newNameOrg = PhotoC360.format("C360_2015-07-25-07-40-28-323_org.jpg");

        assertThat("20150725_074028323_C360.jpg").isEqualTo(newName);
        assertThat("20150725_074028323_C360_org.jpg").isEqualTo(newNameOrg);
    }

    @Test
    public void testRenaming() throws IOException {
        final String userDir = System.getProperty("user.dir");
        final File oldPhoto = new File(userDir, NAME_OLD);
        assertThat(oldPhoto.createNewFile());
        assertThat(oldPhoto.exists());

        final File newPhoto = new File(userDir, PhotoC360.format(NAME_OLD));
        assertThat(oldPhoto.renameTo(newPhoto));
        assertThat(newPhoto.exists());
        assertThat(!oldPhoto.exists());
        assertThat(newPhoto.delete());
        assertThat(!newPhoto.exists());
    }
}
