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
// Generated from file `mt_user_posit_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.userposit;

public class UserPositParam implements java.lang.Cloneable, java.io.Serializable
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

    public String runTaskNum;

    public String
    getRunTaskNum()
    {
        return runTaskNum;
    }

    public void
    setRunTaskNum(String _runTaskNum)
    {
        runTaskNum = _runTaskNum;
    }

    public UserPositParam()
    {
        organId = "";
        userId = "";
        locationId = "";
        unTaskNum = "";
        runTaskNum = "";
    }

    public UserPositParam(String organId, String userId, String locationId, String unTaskNum, String runTaskNum)
    {
        this.organId = organId;
        this.userId = userId;
        this.locationId = locationId;
        this.unTaskNum = unTaskNum;
        this.runTaskNum = runTaskNum;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserPositParam _r = null;
        if(rhs instanceof UserPositParam)
        {
            _r = (UserPositParam)rhs;
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
            if(unTaskNum != _r.unTaskNum)
            {
                if(unTaskNum == null || _r.unTaskNum == null || !unTaskNum.equals(_r.unTaskNum))
                {
                    return false;
                }
            }
            if(runTaskNum != _r.runTaskNum)
            {
                if(runTaskNum == null || _r.runTaskNum == null || !runTaskNum.equals(_r.runTaskNum))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::userposit::UserPositParam");
        __h = IceInternal.HashUtil.hashAdd(__h, organId);
        __h = IceInternal.HashUtil.hashAdd(__h, userId);
        __h = IceInternal.HashUtil.hashAdd(__h, locationId);
        __h = IceInternal.HashUtil.hashAdd(__h, unTaskNum);
        __h = IceInternal.HashUtil.hashAdd(__h, runTaskNum);
        return __h;
    }

    public UserPositParam
    clone()
    {
        UserPositParam c = null;
        try
        {
            c = (UserPositParam)super.clone();
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
        __os.writeString(unTaskNum);
        __os.writeString(runTaskNum);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        organId = __is.readString();
        userId = __is.readString();
        locationId = __is.readString();
        unTaskNum = __is.readString();
        runTaskNum = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserPositParam __v)
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

    static public UserPositParam
    __read(IceInternal.BasicStream __is, UserPositParam __v)
    {
        if(__v == null)
        {
             __v = new UserPositParam();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserPositParam __nullMarshalValue = new UserPositParam();

    public static final long serialVersionUID = -888276447L;
}
