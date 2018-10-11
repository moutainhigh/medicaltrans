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

public class UserPositIce implements java.lang.Cloneable, java.io.Serializable
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

    public String userOrganId;

    public String
    getUserOrganId()
    {
        return userOrganId;
    }

    public void
    setUserOrganId(String _userOrganId)
    {
        userOrganId = _userOrganId;
    }

    public String mac;

    public String
    getMac()
    {
        return mac;
    }

    public void
    setMac(String _mac)
    {
        mac = _mac;
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

    public String userWorkNo;

    public String
    getUserWorkNo()
    {
        return userWorkNo;
    }

    public void
    setUserWorkNo(String _userWorkNo)
    {
        userWorkNo = _userWorkNo;
    }

    public UserPositIce()
    {
        organId = "";
        userId = "";
        locationId = "";
        houseId = "";
        userOrganId = "";
        mac = "";
        userName = "";
        userWorkNo = "";
    }

    public UserPositIce(String organId, String userId, String locationId, String houseId, String userOrganId, String mac, String userName, String userWorkNo)
    {
        this.organId = organId;
        this.userId = userId;
        this.locationId = locationId;
        this.houseId = houseId;
        this.userOrganId = userOrganId;
        this.mac = mac;
        this.userName = userName;
        this.userWorkNo = userWorkNo;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserPositIce _r = null;
        if(rhs instanceof UserPositIce)
        {
            _r = (UserPositIce)rhs;
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
            if(userId != _r.userId)
            {
                if(userId == null || _r.userId == null || !userId.equals(_r.userId))
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
            if(houseId != _r.houseId)
            {
                if(houseId == null || _r.houseId == null || !houseId.equals(_r.houseId))
                {
                    return false;
                }
            }
            if(userOrganId != _r.userOrganId)
            {
                if(userOrganId == null || _r.userOrganId == null || !userOrganId.equals(_r.userOrganId))
                {
                    return false;
                }
            }
            if(mac != _r.mac)
            {
                if(mac == null || _r.mac == null || !mac.equals(_r.mac))
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
            if(userWorkNo != _r.userWorkNo)
            {
                if(userWorkNo == null || _r.userWorkNo == null || !userWorkNo.equals(_r.userWorkNo))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userposit::UserPositIce");
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, locationId);
        __h = IceInternal.HashUtil.hashAdd(__h, houseId);
        __h = IceInternal.HashUtil.hashAdd(__h, userOrganId);
        __h = IceInternal.HashUtil.hashAdd(__h, mac);
        __h = IceInternal.HashUtil.hashAdd(__h, userName);
        __h = IceInternal.HashUtil.hashAdd(__h, userWorkNo);
        return __h;
    }

    public UserPositIce
    clone()
    {
        UserPositIce c = null;
        try
        {
            c = (UserPositIce)super.clone();
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
        __os.writeString(userId);
        __os.writeString(locationId);
        __os.writeString(houseId);
        __os.writeString(userOrganId);
        __os.writeString(mac);
        __os.writeString(userName);
        __os.writeString(userWorkNo);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        organId = __is.readString();
        userId = __is.readString();
        locationId = __is.readString();
        houseId = __is.readString();
        userOrganId = __is.readString();
        mac = __is.readString();
        userName = __is.readString();
        userWorkNo = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserPositIce __v)
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

    static public UserPositIce
    __read(IceInternal.BasicStream __is, UserPositIce __v)
    {
        if(__v == null)
        {
             __v = new UserPositIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserPositIce __nullMarshalValue = new UserPositIce();

    public static final long serialVersionUID = -946814524L;
}