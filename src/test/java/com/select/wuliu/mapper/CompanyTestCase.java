package com.select.wuliu.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.Page;
import com.select.wuliu.entity.BaseEntity;
import com.select.wuliu.entity.Companier;
import com.select.wuliu.entity.Pather;
import com.select.wuliu.entity.Role;
import com.select.wuliu.entity.User;
import com.select.wuliu.entity.area;
import com.select.wuliu.entity.carUser;
import com.select.wuliu.entity.webUser;
import com.select.wuliu.util.HttpUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyTestCase {
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	UserMapper userMapper;
   @Autowired
   RoleMapper roleMapper;
   @Autowired
   loggerMapper loggermapper;
   @Autowired
	WebUserMapper wuserMapper;
   @Autowired
   fahRecorederMapper fahrMapper;
   @Autowired
   carUserMapper carMapper;
   @Autowired
	bCompanyMapper bcompanymapper;
   @Autowired
   areaMapper areaMapper;
   @Test
	public void rds2d() throws IOException{
	   //System.err.println(HttpUtil.get("http://13356958567.chinawutong.com/", "utf-8"));
	   
	   String url = "http://xuhaibo.chinawutong.com/";
       String charset = "utf-8";
       Document rootDoc = HttpUtil.get(url, "utf-8");
       System.err.println("1111111111111111111*************"+rootDoc+"111111111rootDoc");
       if(rootDoc == null) {
          
       }
       Element firstElement = rootDoc.getElementsByClass("SupplyInfor_source").get(0);
      System.err.println("0000000000000000000000*******************"+
    		  firstElement+"0000000000000***********firstElement");
       String yearHref = firstElement.select("a").get(0).attr("href"); // 最近一个年份的省份链接
       Document doc = HttpUtil.get(yearHref, "gb2312");
     //  System.out.println(doc+"doc");
       // 遍历所有的省
       Elements provinceElements = doc.getElementsByClass("provincetr");
      // System.err.println(provinceElements+"provinceElements");
       for (Element element : provinceElements) {
           Elements aEles = element.select("a");
           for (Element aEle : aEles) {
               String name = aEle.text();
               String provincesHref = aEle.attr("href");
               String code = provincesHref.substring(0, provincesHref.indexOf("."));
               int index = yearHref.lastIndexOf("/") + 1;
               provincesHref = yearHref.substring(0, index) + provincesHref;
             
              // getCites(provincesHref, charset, province.getId());
           }
       }
     

	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   //Page<fahRecoreder> a=fahrMapper.findAfdhRecorder("习近平");
	   //Page<fahRecoreder> a=fahrMapper.findAfdhRecorderByi(101);
	  // Page<fahRecoreder> a=fahrMapper.findAfdhRecorderBystatus("等待");
	 // Page<carUser>a=carMapper.findAcarUserByi(114);
	  /*Page<carUser>a=carMapper.findAllcarusers("小型厢车", 114);
	   System.err.println(a);*/
	   
	   
   }
   @Test
 	public void rds2fsww(){
	  // Integer a=companyMapper.deleteCompanyById(28132);
	   //Page<Companier> a=companyMapper.findDelCompanyPByLName("聚时");
	   //Companier  a=companyMapper.findById(28158);
	   //a.setVipClass(1);
	   //Page<webUser> a=wuserMapper.findAllweuser("2");
	   //webUser b=wuserMapper.findwebUserById(89);
	  // List<webUser> a=wuserMapper.findBytype(2);
	   //Integer a=wuserMapper.updatewebUser(89, "0", null, 123, "15236095001", "", "1234567", "", "123", 0, "1", null, "152");
			List<Companier> a= companyMapper.findAll();
			for (Companier company : a) {
				Integer id=company.getCompanyId();
				/*String chf="";
				List<Pather> pa=bcompanymapper.findBywdId(id);
				if(pa.size()!=0){
					for (Pather pather : pa) {
						Integer sta=pather.getSta();
					area are=areaMapper.findByIntegerid(sta);
					String s1=are.getAreaname();
					chf+=s1+"";
					}
				}*/
				String a1=company.getAddress();
				//System.err.println(a1+"a1");
				String chfd;
				if(a1!=null&&!a1.equals("")&&!a1.equals(null)&&a1!=""&&a1.length()>=3){
					
					 chfd=a1.substring(0, 3);
				}else{
					chfd=company.getDepart();
				}
				Integer isDel=company.getIsDel();
				long qz=company.getQz();
				String companyName=company.getCompanyName();
				String address=company.getAddress();
				String contact=company.getContact();
				String telephone=company.getTelephone();
				String phone=company.getPhone();
				String picturePath=company.getPicturePath();
				String directRemark=company.getDirectRemark();
				String transitRemark=company.getTransitRemark();
				String detailPicture=company.getDetailPicture();
				String depart=chfd;
				String alias=company.getAlias();
				String intro=company.getIntro();
				String culture=company.getCulture();
				Integer vipClass=company.getVipClass();
				Integer att=company.getAtt();
				String province=company.getProvince();
				String city=company.getCity();
				Integer type=company.getType();
				String  longitude=company.getLongitude();
				String  dimensions=company.getDimensions();
				String lightCost=company.getLightCost();
				String heavyCost=company.getHeavyCost();
				String aging=company.getAging();
				String style=company.getStyle();
				
				companyMapper.updateCompany(companyName, address, phone, picturePath, telephone, contact, directRemark, transitRemark, depart, alias, detailPicture, qz, intro, culture, vipClass, isDel, att, id, province, city, type, longitude, dimensions, lightCost, heavyCost, aging, style);
				
				
				
				
			}
		//获得所有的	
			
	  /// System.err.println(a);
   }
   
   
   
   @Test
  	public void rds2fsss(){
	   Integer isDel=0;
	   Integer qz=100;
	   Integer companyId=28130;
	   String companyName="郑州聚时物流有限公司";
		String address="南三环";
		String contact="郭经理";
		String telephone="0371-67856114";
		String phone="15638856114";
		String picturePath="www.56114.com";
		String directRemark="郑州";
		String transitRemark="北三环";
		String detailPicture="www.56114.com";
	  //Integer a=companyMapper.updateCompany(companyName, address, phone, picturePath, telephone, contact, directRemark, transitRemark, "","", detailPicture, qz, "", "", 1, isDel, 1, companyId);
	  // System.err.println("--qz----"+a);
	   
	   
   }
   
   
   
   
   
   
   
   
   
   @Test
	public void rds2fss(){
	   //String address="东凤镇";
	   List<Integer> wlId=companyMapper.findBystaAd(35, 36, 2);
	  List<Companier> com=companyMapper.findBystaAndend(wlId);
	  // List<User> com=userMapper.findbycompanyId(1);
	   System.err.println("---com"+com);
   }
   
   
   
   @Test
	public void rds2fd(){
	   String comname="万州";
	  List <Companier> com=companyMapper.findByName(comname);
	   System.err.println("---com---"+com);
   }
   

   @Test
	public void rds2fdl(){
	   String name="14";
	   List <Companier> ld=companyMapper.findByLName(name);
	   System.err.println("----ld---"+ld);
   }
   
   
   
   
   @Test
	public void rds2f(){
	   Integer sta=1;
	   Integer end=130;
	 List<Pather> p=companyMapper.findByft(sta, end);
	   System.err.println("---p--"+p);
   }
   
   @Test
	public void rdsf(){
	  Integer id=1399;
	   Companier c=companyMapper.findById(id);
	   System.err.println(""+c);
   }
   
   
   
   @Test
	public void ra(){
	   List<Companier> lo=companyMapper.findAll();
	   System.err.println("----compannier--"+lo);
	   
   }
   
	@Test
	public void r(){
		Integer rid=56;
		String roleName="管理员";
		String userName="王阳阳";
		Role r=new Role();
		r.setRid(rid);
		r.setRoleName(roleName);
		r.setUserName(userName);
		//Integer r1=roleMapper.addNewRole(r);
		//Integer r1=roleMapper.updateRole(rid, roleName, userName);
		Role r1=roleMapper.findRoleByrid(56);
		System.err.println("----测试角色---"+r);
	}

	@Test
	public void contextLoads() {
		String username="管理员";
		String password="123456";
		String salt="salt";
		
		User u=new User();
		/*u.setUid(1);
		u.setUsername(username);
		u.setPassword(password);
		u.setSalt(salt);
		u.setCreatedTime(CreatedTime);
		u.setCreatedUser(CreatedUser);
		u.setId(1);*/
		/*u.setIsDelete(isDelete);
		u.setModifiedTime(modifiedTime);
		u.setModifiedUser(modifiedUser);*/
		Integer i=userMapper.addNewUser(u);
		System.err.println("---测试增加用户----"+i);
       

	}
	@Test
	public void contex(){
		String cityFrom="郑州";
	       String cityTo="北京";
	       Integer cid=100;
	       Pather path=new Pather();
	      // path.setCid(cid);
	      
	      Integer b= companyMapper.addNewPath(path);
	      System.err.println("---测试新增路线--"+b);
	}

	
	@Test
	public void s(){
		String username="管理员";
		User user=userMapper.findByUserName(username);
		System.err.println("---测试mapper查找用户---"+user);
	}
	
	
	@Test
	public void con(){
		String modifiedUser="王阳阳";
		Date modifiedTime=new Date();
		String companyName="郑州聚时物流有限公司!!";
		String address="南三环";
		String contact="郭经理";
		String telephone="0371-67856114";
		String phone="15638856114";
		String picturePath="www.56114.com";
		String type="注册";
		Companier comp=new Companier();
		BaseEntity ba=new BaseEntity();
		comp.setCompanyName(companyName);
		comp.setAddress(address);
		comp.setContact(contact);
		comp.setPhone(phone);
		comp.setTelephone(telephone);
		comp.setPicturePath(picturePath);
		comp.setQz(System.currentTimeMillis());
		ba.setModifiedTime(modifiedTime);
		ba.setModifiedUser(modifiedUser);
		ba.setType(type);
		ba.setCompany(companyName);
		
	Integer c=	companyMapper.addNewCompany(comp);
	Integer a=loggermapper.addLogger(ba);
	System.err.println("---测试注册公司---"+c);
	System.err.println("---测试注册公司日志---"+a);
		
		
	}
	
	
	
	
	
	
	
	
	
}
