package com.halcyonmobile.rest;

import com.halcyonmobile.model.Owners;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by helmut on 22.05.2017.
 */
public class OwnersServiceTest {

    @Test
    public void findAll() {
        OwnersService service = new OwnersService();
        ArrayList<Owners> all = service.findAll();
        assertNotEquals(all.size(), 0);
    }

    @Test
    public void findByUsername() {
        OwnersService service = new OwnersService();
        Owners byId = service.findByUsername("admin1");
        assertNotNull(byId);
    }

    @Test
    public void addOwnerAndCheckIfInserted() throws UnsupportedEncodingException {
        OwnersService service = new OwnersService();
        service.addOwner("helmut", "helmut", 1);
        Owners result = service.findByUsername("helmut");
        assertNotNull(result);
        //rollback
        service.deleteOwner(result);
    }
}
