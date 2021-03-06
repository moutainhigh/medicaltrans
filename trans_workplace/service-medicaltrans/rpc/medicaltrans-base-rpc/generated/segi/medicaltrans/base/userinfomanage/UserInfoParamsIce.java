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
// Generated from file `userinfomanage.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userinfomanage;

public class UserInfoParamsIce implements java.lang.Cloneable, java.io.Serializable
{
    public String organId;

    public String
    getOrganId()
    {
        return organId;
    }

    public void
    setOrganId(String _organId)
    {
        organId = _organId;
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

    public String pageNo;

    public String
    getPageNo()
    {
        return pageNo;
    }

    public void
    setPageNo(String _pageNo)
    {
        pageNo = _pageNo;
    }

    public String pageLength;

    public String
    getPageLength()
    {
        return pageLength;
    }

    public void
    setPageLength(String _pageLength)
    {
        pageLength = _pageLength;
    }

    public UserInfoParamsIce()
    {
        organId = "";
        userName = "";
        tel = "";
        jobNumber = "";
        status = "";
        pageNo = "";
        pageLength = "";
    }

    public UserInfoParamsIce(String organId, String userName, String tel, String jobNumber, String status, String pageNo, String pageLength)
    {
        this.organId = organId;
        this.userName = userName;
        this.tel = tel;
        this.jobNumber = jobNumber;
        this.status = status;
        this.pageNo = pageNo;
        this.pageLength = pageLength;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserInfoParamsIce _r = null;
        if(rhs instanceof UserInfoParamsIce)
        {
            _r = (UserInfoParamsIce)rhs;
        }

        if(_r != null)
        {
            if(organId != _r.organId)
            {
                if(organId == null || _r.organId == null || !organId.equals(_r.organId))
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
            if(tel != _r.tel)
            {
                if(tel == null || _r.tel == null || !tel.equals(_r.tel))
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
            if(status != _r.status)
            {
                if(status == null || _r.status == null || !status.equals(_r.status))
                {
                    return false;
                }
            }
            if(pageNo != _r.pageNo)
            {
                if(pageNo == null || _r.pageNo == null || !pageNo.equals(_r.pageNo))
                {
                    return false;
                }
            }
            if(pageLength != _r.pageLength)
            {
                if(pageLength == null || _r.pageLength == null || !pageLength.equals(_r.pageLength))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userinfomanage::UserInfoParamsIce");
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, userName);
        __h = IceInternal.HashUtil.hashAdd(__h, tel);
        __h = IceInternal.HashUtil.hashAdd(__h, jobNumber);
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, pageNo);
        __h = IceInternal.HashUtil.hashAdd(__h, pageLength);
        return __h;
    }

    public UserInfoParamsIce
    clone()
    {
        UserInfoParamsIce c = null;
        try
        {
            c = (UserInfoParamsIce)super.clone();
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
        __os.writeString(organId);
        __os.writeString(userName);
        __os.writeString(tel);
        __os.writeString(jobNumber);
        __os.writeString(status);
        __os.writeString(pageNo);
        __os.writeString(pageLength);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        organId = __is.readString();
        userName = __is.readString();
        tel = __is.readString();
        jobNumber = __is.readString();
        status = __is.readString();
        pageNo = __is.readString();
        pageLength = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserInfoParamsIce __v)
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

    static public UserInfoParamsIce
    __read(IceInternal.BasicStream __is, UserInfoParamsIce __v)
    {
        if(__v == null)
        {
             __v = new UserInfoParamsIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserInfoParamsIce __nullMarshalValue = new UserInfoParamsIce();

    public static final long serialVersionUID = 649481084L;
}
