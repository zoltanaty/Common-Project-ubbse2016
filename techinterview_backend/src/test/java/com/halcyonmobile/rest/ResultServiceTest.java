package com.halcyonmobile.rest;

import com.halcyonmobile.model.Result;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by helmut on 22.05.2017.
 */
public class ResultServiceTest {

    @Test
    public void findById() {
        ResultService service = new ResultService();
        Result byId = service.findById(1);
        Result byId1 = service.findById(100);
        assertNotNull(byId);
        assertNull(byId1);
    }

    @Test
    public void findByUserId() {
        ResultService service = new ResultService();
        List<Result> byUserId = service.findByUserId(1);
        assertNotEquals(byUserId.size(), 0);
        List<Result> byUserId1 = service.findByUserId(500);
        assertEquals(byUserId1.size(), 0);
    }

    @Test
    public void findAll(){
        ResultService service = new ResultService();
        List<Result> all = service.findAll();
        assertEquals(all.size(),5);
    }
}
