package com.maker.lp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.maker.lp.entity.Person;
import com.maker.lp.service.PersonService;
import org.maker.lp.R;
import org.maker.lp.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("pms/index")
public class Login {

    //访问路径：http://10.100.42.253:8010/pms/index

    @Autowired
    PersonService personService;



    @GetMapping("login/{personId}/{password}")
    public R login(@PathVariable Integer personId, @PathVariable String password){
        LambdaQueryWrapper<Person> pq = new LambdaQueryWrapper<>();
        password= MD5.inputPassToFormPass(password);
        pq.eq(Person::getId,personId);
        pq.eq(Person::getPassword,password);
        List<Person> list = personService.list(pq);
        if(list.size()==0){
            return R.error();
        }
        return R.ok().data("items",list);
    }
}
