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

public class SumLocationListIce implements java.lang.Cloneable, java.io.Serializable
{
    public String loactionId;

    public String
    getLoactionId()
    {
        return loactionId;
    }

    public void
    setLoactionId(String _loactionId)
    {
        loactionId = _loactionId;
    }

    public String loactionName;

    public String
    getLoactionName()
    {
        return loactionName;
    }

    public void
    setLoactionName(String _loactionName)
    {
        loactionName = _loactionName;
    }

    public String floorPositX;

    public String
    getFloorPositX()
    {
        return floorPositX;
    }

    public void
    setFloorPositX(String _floorPositX)
    {
        floorPositX = _floorPositX;
    }

    public String floorPositY;

    public String
    getFloorPositY()
    {
        return floorPositY;
    }

    public void
    setFloorPositY(String _floorPositY)
    {
        floorPositY = _floorPositY;
    }

    public String userListSize;

    public String
    getUserListSize()
    {
        return userListSize;
    }

    public void
    setUserListSize(String _userListSize)
    {
        userListSize = _userListSize;
    }

    public java.util.List<UserSumLocationListIce> userSumLocationListIceList;

    public java.util.List<UserSumLocationListIce>
    getUserSumLocationListIceList()
    {
        return userSumLocationListIceList;
    }

    public void
    setUserSumLocationListIceList(java.util.List<UserSumLocationListIce> _userSumLocationListIceList)
    {
        userSumLocationListIceList = _userSumLocationListIceList;
    }

    public SumLocationListIce()
    {
        loactionId = "";
        loactionName = "";
        floorPositX = "";
        floorPositY = "";
        userListSize = "";
    }

    public SumLocationListIce(String loactionId, String loactionName, String floorPositX, String floorPositY, String userListSize, java.util.List<UserSumLocationListIce> userSumLocationListIceList)
    {
        this.loactionId = loactionId;
        this.loactionName = loactionName;
        this.floorPositX = floorPositX;
        this.floorPositY = floorPositY;
        this.userListSize = userListSize;
        this.userSumLocationListIceList = userSumLocationListIceList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        SumLocationListIce _r = null;
        if(rhs instanceof SumLocationListIce)
        {
            _r = (SumLocationListIce)rhs;
        }

        if(_r != null)
        {
            if(loactionId != _r.loactionId)
            {
                if(loactionId == null || _r.loactionId == null || !loactionId.equals(_r.loactionId))
                {
                    return false;
                }
            }
            if(loactionName != _r.loactionName)
            {
                if(loactionName == null || _r.loactionName == null || !loactionName.equals(_r.loactionName))
                {
                    return false;
                }
            }
            if(floorPositX != _r.floorPositX)
            {
                if(floorPositX == null || _r.floorPositX == null || !floorPositX.equals(_r.floorPositX))
                {
                    return false;
                }
            }
            if(floorPositY != _r.floorPositY)
            {
                if(floorPositY == null || _r.floorPositY == null || !floorPositY.equals(_r.floorPositY))
                {
                    return false;
                }
            }
            if(userListSize != _r.userListSize)
            {
                if(userListSize == null || _r.userListSize == null || !userListSize.equals(_r.userListSize))
                {
                    return false;
                }
            }
            if(userSumLocationListIceList != _r.userSumLocationListIceList)
            {
                if(userSumLocationListIceList == null || _r.userSumLocationListIceList == null || !userSumLocationListIceList.equals(_r.userSumLocationListIceList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userposit::SumLocationListIce");
        __h = IceInternal.HashUtil.hashAdd(__h, loactionId);
        __h = IceInternal.HashUtil.hashAdd(__h, loactionName);
        __h = IceInternal.HashUtil.hashAdd(__h, floorPositX);
        __h = IceInternal.HashUtil.hashAdd(__h, floorPositY);
        __h = IceInternal.HashUtil.hashAdd(__h, userListSize);
        __h = IceInternal.HashUtil.hashAdd(__h, userSumLocationListIceList);
        return __h;
    }

    public SumLocationListIce
    clone()
    {
        SumLocationListIce c = null;
        try
        {
            c = (SumLocationListIce)super.clone();
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
        __os.writeString(loactionId);
        __os.writeString(loactionName);
        __os.writeString(floorPositX);
        __os.writeString(floorPositY);
        __os.writeString(userListSize);
        UserSumLocationListIceListHelper.write(__os, userSumLocationListIceList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        loactionId = __is.readString();
        loactionName = __is.readString();
        floorPositX = __is.readString();
        floorPositY = __is.readString();
        userListSize = __is.readString();
        userSumLocationListIceList = UserSumLocationListIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, SumLocationListIce __v)
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

    static public SumLocationListIce
    __read(IceInternal.BasicStream __is, SumLocationListIce __v)
    {
        if(__v == null)
        {
             __v = new SumLocationListIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final SumLocationListIce __nullMarshalValue = new SumLocationListIce();

    public static final long serialVersionUID = -1055521607L;
}
