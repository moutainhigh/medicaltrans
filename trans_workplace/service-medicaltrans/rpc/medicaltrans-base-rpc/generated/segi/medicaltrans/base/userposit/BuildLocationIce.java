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

public class BuildLocationIce implements java.lang.Cloneable, java.io.Serializable
{
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

    public java.util.List<UserListIce> userListIceList;

    public java.util.List<UserListIce>
    getUserListIceList()
    {
        return userListIceList;
    }

    public void
    setUserListIceList(java.util.List<UserListIce> _userListIceList)
    {
        userListIceList = _userListIceList;
    }

    public BuildLocationIce()
    {
        locationId = "";
        locationName = "";
    }

    public BuildLocationIce(String locationId, String locationName, java.util.List<UserListIce> userListIceList)
    {
        this.locationId = locationId;
        this.locationName = locationName;
        this.userListIceList = userListIceList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        BuildLocationIce _r = null;
        if(rhs instanceof BuildLocationIce)
        {
            _r = (BuildLocationIce)rhs;
        }

        if(_r != null)
        {
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
            if(userListIceList != _r.userListIceList)
            {
                if(userListIceList == null || _r.userListIceList == null || !userListIceList.equals(_r.userListIceList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userposit::BuildLocationIce");
        __h = IceInternal.HashUtil.hashAdd(__h, locationId);
        __h = IceInternal.HashUtil.hashAdd(__h, locationName);
        __h = IceInternal.HashUtil.hashAdd(__h, userListIceList);
        return __h;
    }

    public BuildLocationIce
    clone()
    {
        BuildLocationIce c = null;
        try
        {
            c = (BuildLocationIce)super.clone();
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
        __os.writeString(locationId);
        __os.writeString(locationName);
        UserListIceListHelper.write(__os, userListIceList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        locationId = __is.readString();
        locationName = __is.readString();
        userListIceList = UserListIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, BuildLocationIce __v)
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

    static public BuildLocationIce
    __read(IceInternal.BasicStream __is, BuildLocationIce __v)
    {
        if(__v == null)
        {
             __v = new BuildLocationIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final BuildLocationIce __nullMarshalValue = new BuildLocationIce();

    public static final long serialVersionUID = 27713440L;
}
