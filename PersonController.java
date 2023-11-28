package com.maker.lp.controller;

import com.maker.lp.entity.Person;
import com.maker.lp.entity.Personnel;
import com.maker.lp.service.PersonService;
import com.maker.lp.service.PersonnelService;
import org.maker.lp.R;
import org.maker.lp.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/pms/person")
public class PersonController {

    //访问路径：http://10.100.42.253:8010/pms/person
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonnelService personnelService;

    //查询所有员工信息
    @GetMapping("selectAll")
    public R selectAll(){
        List<Person> list = personService.list(null);
        return R.ok().data("item",list);
    }


    //根据id查员工
    @GetMapping("selectPerson/{id}")
    public R selectById(@PathVariable Integer id){
        Person person = personService.getById(id);
        if(person==null){
            return R.error();
        }
        List<Person> list=new ArrayList<>();
        list.add(person);
        return R.ok().data("item",list);

    }

    //根据id删除员工
    @DeleteMapping("delete/{id}")
    public R deleteById(@PathVariable Integer id){
        Person person = personService.getById(id);
        boolean flag = personService.removeById(id);
        if (flag) {
            Random random = new Random();
            int id2=random.nextInt(999999)+1;

            System.out.println("id"+id);
            System.out.println("person"+person);
            String name1=person.getName();
            String des=name1+"被辞退";
            Personnel personnel = new Personnel();
            personnel.setId(id2);
            personnel.setPerson(id);
            personnel.setChangeCode(2);
            personnel.setDescription(des);
            boolean flag1 = personnelService.save(personnel);
            return R.ok();
        }
        else
            return R.error();

    }

    //添加员工
    @PostMapping("add")
    public R addPerson(@RequestBody Person person)  {
        String pw=MD5.inputPassToFormPass("123456");
        System.out.println("pw:"+pw);
        //d3b1294a61a07da9b49b6e22b2cbd7f9
        //517a62db802d61980dd0e4da0cf37852
        //517a62db802d61980dd0e4da0cf37852
        person.setPassword(MD5.inputPassToFormPass(person.getPassword()));
        boolean save = personService.save(person);
        if (save) {
            Random random = new Random();
            int id2=random.nextInt(999999)+1;
            int id1=person.getId();
            String name1=person.getName();
            String des=name1+"加入公司";
            Personnel personnel = new Personnel();
            personnel.setId(id2);
            personnel.setPerson(id1);
            personnel.setChangeCode(0);
            personnel.setDescription(des);
            boolean flag = personnelService.save(personnel);
            return R.ok().data("person", person);
        } else {
            return R.error();
        }

    }

    //修改员工
    @PostMapping("update")
    public R updatePerson(@RequestBody Person person){
        person.setPassword(MD5.inputPassToFormPass(person.getPassword()));
        Integer id = person.getId();
        Person personbefore = personService.getById(id);
        Integer depbefore=personbefore.getDepartment();
        Integer depafter=person.getDepartment();
        if(!(depafter==depbefore)){
            Random random = new Random();
            int id2=random.nextInt(999999)+1;
            String name1=person.getName();
            String des=name1+"更改部门";
            Personnel personnel = new Personnel();
            personnel.setId(id2);
            personnel.setPerson(id);
            personnel.setChangeCode(1);
            personnel.setDescription(des);
            boolean flag = personnelService.save(personnel);
        }
        boolean flag = personService.updateById(person);
        if (flag)
            return R.ok();
        else
            return R.error();


    }

}
