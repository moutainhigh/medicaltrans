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
// Generated from file `mt_userhosp_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.userhosp;

public class UserHospCommonIce implements java.lang.Cloneable, java.io.Serializable
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

    public String organName;

    public String
    getOrganName()
    {
        return organName;
    }

    public void
    setOrganName(String _organName)
    {
        organName = _organName;
    }

    public String houseId;

    public String
    getHouseId()
    {
        return houseId;
    }

    public void
    setHouseId(String _houseId)
    {
        houseId = _houseId;
    }

    public String houseName;

    public String
    getHouseName()
    {
        return houseName;
    }

    public void
    setHouseName(String _houseName)
    {
        houseName = _houseName;
    }

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

    public UserHospCommonIce()
    {
        organId = "";
        organName = "";
        houseId = "";
        houseName = "";
        userId = "";
        userName = "";
    }

    public UserHospCommonIce(String organId, String organName, String houseId, String houseName, String userId, String userName)
    {
        this.organId = organId;
        this.organName = organName;
        this.houseId = houseId;
        this.houseName = houseName;
        this.userId = userId;
        this.userName = userName;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserHospCommonIce _r = null;
        if(rhs instanceof UserHospCommonIce)
        {
            _r = (UserHospCommonIce)rhs;
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
            if(organName != _r.organName)
            {
                if(organName == null || _r.organName == null || !organName.equals(_r.organName))
                {
                    return false;
                }
            }
            if(houseId != _r.houseId)
            {
                if(houseId == null || _r.houseId == null || !houseId.equals(_r.houseId))
                {
                    return false;
                }
            }
            if(houseName != _r.houseName)
            {
                if(houseName == null || _r.houseName == null || !houseName.equals(_r.houseName))
                {
                    return false;
                }
            }
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::userhosp::UserHospCommonIce");
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, organName);
        __h = IceInternal.HashUtil.hashAdd(__h, houseId);
        __h = IceInternal.HashUtil.hashAdd(__h, houseName);
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, userName);
        return __h;
    }

    public UserHospCommonIce
    clone()
    {
        UserHospCommonIce c = null;
        try
        {
            c = (UserHospCommonIce)super.clone();
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
        __os.writeString(organName);
        __os.writeString(houseId);
        __os.writeString(houseName);
        __os.writeString(userId);
        __os.writeString(userName);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        organId = __is.readString();
        organName = __is.readString();
        houseId = __is.readString();
        houseName = __is.readString();
        userId = __is.readString();
        userName = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserHospCommonIce __v)
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

    static public UserHospCommonIce
    __read(IceInternal.BasicStream __is, UserHospCommonIce __v)
    {
        if(__v == null)
        {
             __v = new UserHospCommonIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserHospCommonIce __nullMarshalValue = new UserHospCommonIce();

    public static final long serialVersionUID = -576374297L;
}
