// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `userposit.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userposit;

public class UserInfoIce implements java.lang.Cloneable, java.io.Serializable
{
    public String userId;

    public String
    getUserId()
    {
        return userId;
    }

    public void
    setUserId(String _userId)
    {
        userId = _userId;
    }

    public String userName;

    public String
    getUserName()
    {
        return userName;
    }

    public void
    setUserName(String _userName)
    {
        userName = _userName;
    }

    public String nickName;

    public String
    getNickName()
    {
        return nickName;
    }

    public void
    setNickName(String _nickName)
    {
        nickName = _nickName;
    }

    public String sex;

    public String
    getSex()
    {
        return sex;
    }

    public void
    setSex(String _sex)
    {
        sex = _sex;
    }

    public String tel;

    public String
    getTel()
    {
        return tel;
    }

    public void
    setTel(String _tel)
    {
        tel = _tel;
    }

    public String qq;

    public String
    getQq()
    {
        return qq;
    }

    public void
    setQq(String _qq)
    {
        qq = _qq;
    }

    public String email;

    public String
    getEmail()
    {
        return email;
    }

    public void
    setEmail(String _email)
    {
        email = _email;
    }

    public String jobNumber;

    public String
    getJobNumber()
    {
        return jobNumber;
    }

    public void
    setJobNumber(String _jobNumber)
    {
        jobNumber = _jobNumber;
    }

    public String groupId;

    public String
    getGroupId()
    {
        return groupId;
    }

    public void
    setGroupId(String _groupId)
    {
        groupId = _groupId;
    }

    public String groupName;

    public String
    getGroupName()
    {
        return groupName;
    }

    public void
    setGroupName(String _groupName)
    {
        groupName = _groupName;
    }

    public String userSexName;

    public String
    getUserSexName()
    {
        return userSexName;
    }

    public void
    setUserSexName(String _userSexName)
    {
        userSexName = _userSexName;
    }

    public String status;

    public String
    getStatus()
    {
        return status;
    }

    public void
    setStatus(String _status)
    {
        status = _status;
    }

    public String statusName;

    public String
    getStatusName()
    {
        return statusName;
    }

    public void
    setStatusName(String _statusName)
    {
        statusName = _statusName;
    }

    public String unTaskNum;

    public String
    getUnTaskNum()
    {
        return unTaskNum;
    }

    public void
    setUnTaskNum(String _unTaskNum)
    {
        unTaskNum = _unTaskNum;
    }

    public String locationId;

    public String
    getLocationId()
    {
        return locationId;
    }

    public void
    setLocationId(String _locationId)
    {
        locationId = _locationId;
    }

    public String locationName;

    public String
    getLocationName()
    {
        return locationName;
    }

    public void
    setLocationName(String _locationName)
    {
        locationName = _locationName;
    }

    public UserInfoIce()
    {
        userId = "";
        userName = "";
        nickName = "";
        sex = "";
        tel = "";
        qq = "";
        email = "";
        jobNumber = "";
        groupId = "";
        groupName = "";
        userSexName = "";
        status = "";
        statusName = "";
        unTaskNum = "";
        locationId = "";
        locationName = "";
    }

    public UserInfoIce(String userId, String userName, String nickName, String sex, String tel, String qq, String email, String jobNumber, String groupId, String groupName, String userSexName, String status, String statusName, String unTaskNum, String locationId, String locationName)
    {
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.sex = sex;
        this.tel = tel;
        this.qq = qq;
        this.email = email;
        this.jobNumber = jobNumber;
        this.groupId = groupId;
        this.groupName = groupName;
        this.userSexName = userSexName;
        this.status = status;
        this.statusName = statusName;
        this.unTaskNum = unTaskNum;
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserInfoIce _r = null;
        if(rhs instanceof UserInfoIce)
        {
            _r = (UserInfoIce)rhs;
        }

        if(_r != null)
        {
            if(userId != _r.userId)
            {
                if(userId == null || _r.userId == null || !userId.equals(_r.userId))
                {
                    return false;
                }
            }
            if(userName != _r.userName)
            {
                if(userName == null || _r.userName == null || !userName.equals(_r.userName))
                {
                    return false;
                }
            }
            if(nickName != _r.nickName)
            {
                if(nickName == null || _r.nickName == null || !nickName.equals(_r.nickName))
                {
                    return false;
                }
            }
            if(sex != _r.sex)
            {
                if(sex == null || _r.sex == null || !sex.equals(_r.sex))
                {
                    return false;
                }
            }
            if(tel != _r.tel)
            {
                if(tel == null || _r.tel == null || !tel.equals(_r.tel))
                {
                    return false;
                }
            }
            if(qq != _r.qq)
            {
                if(qq == null || _r.qq == null || !qq.equals(_r.qq))
                {
                    return false;
                }
            }
            if(email != _r.email)
            {
                if(email == null || _r.email == null || !email.equals(_r.email))
                {
                    return false;
                }
            }
            if(jobNumber != _r.jobNumber)
            {
                if(jobNumber == null || _r.jobNumber == null || !jobNumber.equals(_r.jobNumber))
                {
                    return false;
                }
            }
            if(groupId != _r.groupId)
            {
                if(groupId == null || _r.groupId == null || !groupId.equals(_r.groupId))
                {
                    return false;
                }
            }
            if(groupName != _r.groupName)
            {
                if(groupName == null || _r.groupName == null || !groupName.equals(_r.groupName))
                {
                    return false;
                }
            }
            if(userSexName != _r.userSexName)
            {
                if(userSexName == null || _r.userSexName == null || !userSexName.equals(_r.userSexName))
                {
                    return false;
                }
            }
            if(status != _r.status)
            {
                if(status == null || _r.status == null || !status.equals(_r.status))
                {
                    return false;
                }
            }
            if(statusName != _r.statusName)
            {
                if(statusName == null || _r.statusName == null || !statusName.equals(_r.statusName))
                {
                    return false;
                }
            }
            if(unTaskNum != _r.unTaskNum)
            {
                if(unTaskNum == null || _r.unTaskNum == null || !unTaskNum.equals(_r.unTaskNum))
                {
                    return false;
                }
            }
            if(locationId != _r.locationId)
            {
                if(locationId == null || _r.locationId == null || !locationId.equals(_r.locationId))
                {
                    return false;
                }
            }
            if(locationName != _r.locationName)
            {
                if(locationName == null || _r.locationName == null || !locationName.equals(_r.locationName))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userposit::UserInfoIce");
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, userName);
        __h = IceInternal.HashUtil.hashAdd(__h, nickName);
        __h = IceInternal.HashUtil.hashAdd(__h, sex);
        __h = IceInternal.HashUtil.hashAdd(__h, tel);
        __h = IceInternal.HashUtil.hashAdd(__h, qq);
        __h = IceInternal.HashUtil.hashAdd(__h, email);
        __h = IceInternal.HashUtil.hashAdd(__h, jobNumber);
        __h = IceInternal.HashUtil.hashAdd(__h, groupId);
        __h = IceInternal.HashUtil.hashAdd(__h, groupName);
        __h = IceInternal.HashUtil.hashAdd(__h, userSexName);
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, statusName);
        __h = IceInternal.HashUtil.hashAdd(__h, unTaskNum);
        __h = IceInternal.HashUtil.hashAdd(__h, locationId);
        __h = IceInternal.HashUtil.hashAdd(__h, locationName);
        return __h;
    }

    public UserInfoIce
    clone()
    {
        UserInfoIce c = null;
        try
        {
            c = (UserInfoIce)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(userId);
        __os.writeString(userName);
        __os.writeString(nickName);
        __os.writeString(sex);
        __os.writeString(tel);
        __os.writeString(qq);
        __os.writeString(email);
        __os.writeString(jobNumber);
        __os.writeString(groupId);
        __os.writeString(groupName);
        __os.writeString(userSexName);
        __os.writeString(status);
        __os.writeString(statusName);
        __os.writeString(unTaskNum);
        __os.writeString(locationId);
        __os.writeString(locationName);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        userId = __is.readString();
        userName = __is.readString();
        nickName = __is.readString();
        sex = __is.readString();
        tel = __is.readString();
        qq = __is.readString();
        email = __is.readString();
        jobNumber = __is.readString();
        groupId = __is.readString();
        groupName = __is.readString();
        userSexName = __is.readString();
        status = __is.readString();
        statusName = __is.readString();
        unTaskNum = __is.readString();
        locationId = __is.readString();
        locationName = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserInfoIce __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public UserInfoIce
    __read(IceInternal.BasicStream __is, UserInfoIce __v)
    {
        if(__v == null)
        {
             __v = new UserInfoIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserInfoIce __nullMarshalValue = new UserInfoIce();

    public static final long serialVersionUID = -830876159L;
}
