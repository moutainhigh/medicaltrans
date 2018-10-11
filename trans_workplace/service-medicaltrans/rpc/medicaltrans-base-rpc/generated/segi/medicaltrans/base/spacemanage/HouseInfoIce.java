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
// Generated from file `spacemanage.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.spacemanage;

public class HouseInfoIce implements java.lang.Cloneable, java.io.Serializable
{
    public String floorId;

    public String
    getFloorId()
    {
        return floorId;
    }

    public void
    setFloorId(String _floorId)
    {
        floorId = _floorId;
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

    public String sortNo;

    public String
    getSortNo()
    {
        return sortNo;
    }

    public void
    setSortNo(String _sortNo)
    {
        sortNo = _sortNo;
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

    public HouseInfoIce()
    {
        floorId = "";
        houseId = "";
        houseName = "";
        sortNo = "";
        status = "";
        statusName = "";
    }

    public HouseInfoIce(String floorId, String houseId, String houseName, String sortNo, String status, String statusName)
    {
        this.floorId = floorId;
        this.houseId = houseId;
        this.houseName = houseName;
        this.sortNo = sortNo;
        this.status = status;
        this.statusName = statusName;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        HouseInfoIce _r = null;
        if(rhs instanceof HouseInfoIce)
        {
            _r = (HouseInfoIce)rhs;
        }

        if(_r != null)
        {
            if(floorId != _r.floorId)
            {
                if(floorId == null || _r.floorId == null || !floorId.equals(_r.floorId))
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
            if(sortNo != _r.sortNo)
            {
                if(sortNo == null || _r.sortNo == null || !sortNo.equals(_r.sortNo))
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::spacemanage::HouseInfoIce");
        __h = IceInternal.HashUtil.hashAdd(__h, floorId);
        __h = IceInternal.HashUtil.hashAdd(__h, houseId);
        __h = IceInternal.HashUtil.hashAdd(__h, houseName);
        __h = IceInternal.HashUtil.hashAdd(__h, sortNo);
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, statusName);
        return __h;
    }

    public HouseInfoIce
    clone()
    {
        HouseInfoIce c = null;
        try
        {
            c = (HouseInfoIce)super.clone();
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
        __os.writeString(floorId);
        __os.writeString(houseId);
        __os.writeString(houseName);
        __os.writeString(sortNo);
        __os.writeString(status);
        __os.writeString(statusName);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        floorId = __is.readString();
        houseId = __is.readString();
        houseName = __is.readString();
        sortNo = __is.readString();
        status = __is.readString();
        statusName = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, HouseInfoIce __v)
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

    static public HouseInfoIce
    __read(IceInternal.BasicStream __is, HouseInfoIce __v)
    {
        if(__v == null)
        {
             __v = new HouseInfoIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final HouseInfoIce __nullMarshalValue = new HouseInfoIce();

    public static final long serialVersionUID = 1369037524L;
}