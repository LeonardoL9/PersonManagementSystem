package main.service.Impl;

import main.entity.Person;
import main.entity.Personnel;
import main.service.Interfaces.pms_service;
import main.util.DBconnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonService implements pms_service {
    @Override
    public void registration() throws ClassNotFoundException, SQLException{
        Person person = inputUI();
        DBconnector db = new DBconnector();
        Connection con = db.connect();
        String sql="insert into person(authority,name,sex,birthday,department,job,edu_level,spcialty,address,tel,email,state,remark) values(?,?,?,?,?,?,?,?,?,?,?,'T',?)";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1,person.getAuthority());

        pstmt.setString(2,person.getName());

        pstmt.setString(3,person.getSex());

        pstmt.setDate(4, person.getBirthday());

        pstmt.setInt(5,person.getDepartment());

        pstmt.setInt(6,person.getJob());

        pstmt.setInt(7,person.getEdu_level());

        pstmt.setString(8,person.getSpcialty());

        pstmt.setString(9,person.getAddress());

        pstmt.setLong(10,person.getTel());

        pstmt.setString(11,person.getEmail());

        pstmt.setString(12,person.getRemark());
        boolean execute = pstmt.execute();
        if(execute){
            System.out.println("添加成功");
        }
        else{
            System.out.println("添加失败");
        }
        pstmt.close();
        con.close();
    }
    public Person inputUI() {
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入用户权限：");
        String authority= sc.next();

        System.out.println("请输入姓名：");
        String name= sc.next();

        System.out.println("请输入性别：");
        String sex= sc.next();

        System.out.println("请按照“年-月-日”的格式输入生日：");
        Date birthday = Date.valueOf(sc.next());

        System.out.println("请输入所在部门：");
        Integer department= sc.nextInt();

        System.out.println("请输入职务：");
        int job= sc.nextInt();

        System.out.println("请输入受教育程度代码：");
        System.out.println("0-小学  1-初中  2-高中  3-职高  4-大本");
        System.out.println("5-大专  6-硕士  7-博士  8-博士后");
        int edu_level= sc.nextInt();

        System.out.println("请输入专业技能：");
        String spcialty= sc.next();

        System.out.println("请输入家庭住址：");
        String address= sc.next();

        System.out.println("请输入联系电话：");
        Integer tel=sc.nextInt();

        System.out.println("请输入电子邮箱：");
        String email= sc.next();

        System.out.println("请输入备注：");
        String remark=sc.next();
        sc.close();

        return new Person(authority,name,sex, birthday,department,job,edu_level,spcialty,address,tel,email,'T',remark);

    }
    @Override
    public void personChange() throws SQLException, ClassNotFoundException {
        Personnel personnel = personnelChangeUI();
        DBconnector dBconnector = new DBconnector();
        Connection con = dBconnector.connect();
        String sql="insert into personnel(person,`change`,description) values(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,personnel.getPerson_id());
        pstmt.setInt(2,personnel.getChangeCode());
        pstmt.setString(3,personnel.getDescription());
        boolean execute = pstmt.execute();
        if(!execute){
            System.out.println("添加成功");
        }
        else{
            System.out.println("添加失败");
        }

        pstmt.close();
        con.close();
    }

    private Personnel personnelChangeUI() {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入员工号：");
        Integer personID=sc.nextInt();
        //判断员工是否输入正确 judgeID()

        System.out.println("请输入变更代码（0-新员工加入 1-职务变动 2-辞退）：");
        Integer changeCode=sc.nextInt();
        //判断代码是否输入正确 judgeChangeCode();

        System.out.println("请输入详细记录：");
        String description=sc.next();
        return new Personnel(personID,changeCode,description);

    }

    @Override
    public Person selectPersonById(Integer id) throws ClassNotFoundException, SQLException {
        DBconnector dBconnector = new DBconnector();
        Connection con = dBconnector.connect();
        String sql="select * from person where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet re = pstmt.executeQuery();
        if(re.getRow()==0){
            System.out.println("员工号输入错误");
            return null;
        }
        else {
            re.next();
            Person person = new Person();
            person.setId(id);
            person.setPassword(re.getString("password"));
            person.setAuthority(re.getString("authority"));
            person.setName(re.getString("name"));
            person.setSex(re.getString("sex"));
            person.setBirthday(re.getDate("birthday"));
            person.setDepartment(re.getInt("department"));
            person.setJob(re.getInt("job"));
            person.setEdu_level(re.getInt("edu_level"));
            person.setSpcialty(re.getString("spcialty"));
            person.setAddress(re.getString("address"));
            person.setTel(re.getLong("tel"));
            person.setEmail(re.getString("email"));
            byte state = re.getByte("state");
            person.setState((char) state);
            person.setRemark(re.getString("remark"));
            pstmt.close();
            con.close();
            return person;
        }
    }

    @Override
    public List<Person> selectAll() throws ClassNotFoundException, SQLException {
        DBconnector dBconnector = new DBconnector();
        Connection con = dBconnector.connect();
        String sql="select * from person";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet re = pstmt.executeQuery();
        List<Person> personList=new ArrayList<>();
        while(re.next()) {
            Person person = new Person();
            person.setId(re.getInt("id"));
            person.setPassword(re.getString("password"));
            person.setAuthority(re.getString("authority"));
            person.setName(re.getString("name"));
            person.setSex(re.getString("sex"));
            person.setBirthday(re.getDate("birthday"));
            person.setDepartment(re.getInt("department"));
            person.setJob(re.getInt("job"));
            person.setEdu_level(re.getInt("edu_level"));
            person.setSpcialty(re.getString("spcialty"));
            person.setAddress(re.getString("address"));
            person.setTel(re.getLong("tel"));
            person.setEmail(re.getString("email"));
            byte state = re.getByte("state");
            person.setState((char) state);
            person.setRemark(re.getString("remark"));
            personList.add(person);
        }
        pstmt.close();
        con.close();
        return personList;
    }

    @Override
    public void removeById(Integer id) throws ClassNotFoundException, SQLException {
        DBconnector dBconnector = new DBconnector();
        Connection con = dBconnector.connect();
        String sql="delete from person where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,id);
        boolean execute = pstmt.execute();
        if(execute){
            System.out.println("删除失败");
        }
        else{
            System.out.println("删除成功");
        }
        pstmt.close();
        con.close();
    }

    @Override
    public void updateById(Integer id) throws SQLException, ClassNotFoundException{
        if(!judgeId(id)) {
            System.out.println("员工号不存在");
        }
        else{
            registration();
        }
    }

    public boolean judgeId(Integer id) throws SQLException, ClassNotFoundException {
        List<Person> personList = selectAll();
        for (Person person : personList) {
            if(person.getId()==id)
                return true;
        }
        return false;
    }
}
