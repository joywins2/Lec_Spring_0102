package org.joy.domain;

/*
 * ...628p.
	create table ztbl_user (
	  uid varchar(50) not null,
	  upw varchar(50) not null,
	  uname varchar(100) not null,
	  upoint int not null default 0,
	  primary key(uid)
	);
	
	insert into ztbl_user(uid, upw, uname) values('user00', 'user00', 'Iron Man');
	insert into ztbl_user(uid, upw, uname) values('user01', 'user01', 'Captain');
	insert into ztbl_user(uid, upw, uname) values('user02', 'user02', 'Hulk');
	insert into ztbl_user(uid, upw, uname) values('user03', 'user03', 'Thor');
	insert into ztbl_user(uid, upw, uname) values('user10', 'user10', 'Quick Silver');
	
	select * from ztbl_user;
 */

public class UserVO {

  private String uid;
  private String upw;
  private String uname;
  private int upoint;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getUpw() {
    return upw;
  }

  public void setUpw(String upw) {
    this.upw = upw;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public int getUpoint() {
    return upoint;
  }

  public void setUpoint(int upoint) {
    this.upoint = upoint;
  }

  @Override
  public String toString() {
    return "UserVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + "]";
  }
}
