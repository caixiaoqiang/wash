package com.cookie.wash.controller;

import com.cookie.wash.domian.Account;
import com.cookie.wash.exceptions.ResultException;
import com.cookie.wash.result.TResult;
import com.cookie.wash.service.AccountService;
import com.cookie.wash.service.ColthesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/colthes")
public class ColthesController {

    @Autowired
    private ColthesService  colthesService ;

    @PostMapping("/{type}")
    public TResult<Integer> add(@RequestParam("name") String name ,
                                @PathVariable("type") int type ) throws ResultException {

        return new TResult<>(type == 1 ? colthesService.addColor(name) : colthesService.addColthes(name));
    }

    @GetMapping("/{type}")
    public TResult<Map<String,Object>> get(@RequestParam("keyword") String keyword ,
                            @PathVariable("type") int type ,
                            @RequestParam("pageNumber") int pageNumber ,
                            @RequestParam("pageSize") int pageSize ) throws ResultException {

        return new TResult<>(type == 1 ? colthesService.getColors(keyword,pageNumber,pageSize) : colthesService.getClothes(keyword,pageNumber,pageSize));
    }


}
