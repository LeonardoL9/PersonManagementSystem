package com.maker.lp.controller;

import com.maker.lp.entity.Personnel;
import com.maker.lp.service.PersonnelService;
import org.maker.lp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("pms/personnel")
public class PersonnelController {

    //访问地址 http://10.100.42.253:8010/pms/personnel
    @Autowired
    private PersonnelService personnelService;

    //查询所有变更记录
    @GetMapping("/selectAll")
    public List<Personnel> selectAll(){
        List<Personnel> list = personnelService.list();
        return list;
    }

    //2.添加变更记录
    @PostMapping("addPersonnel")
    public  R addPersonnel(@RequestBody Personnel personnel) {
        System.out.println(personnel);
           boolean save = personnelService.save(personnel);
//        return save;

        if(save)
            return R.ok();
        else
            return R.error();
    }

    //3.根据id查询记录
    @GetMapping("getPersonnelById/{personid}")
    public List<Personnel> getPersonnelById(@PathVariable int personid) {
        List<Personnel> personnels = personnelService.lambdaQuery()
                .eq(Personnel::getPerson, personid)
                .list();
        return personnels;
    }

}
