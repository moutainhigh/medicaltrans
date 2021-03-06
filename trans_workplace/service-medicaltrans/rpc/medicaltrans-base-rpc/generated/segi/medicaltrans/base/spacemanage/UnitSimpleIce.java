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

public class UnitSimpleIce implements java.lang.Cloneable, java.io.Serializable
{
    public String unitId;

    public String
    getUnitId()
    {
        return unitId;
    }

    public void
    setUnitId(String _unitId)
    {
        unitId = _unitId;
    }

    public String unitName;

    public String
    getUnitName()
    {
        return unitName;
    }

    public void
    setUnitName(String _unitName)
    {
        unitName = _unitName;
    }

    public String buildId;

    public String
    getBuildId()
    {
        return buildId;
    }

    public void
    setBuildId(String _buildId)
    {
        buildId = _buildId;
    }

    public UnitSimpleIce()
    {
        unitId = "";
        unitName = "";
        buildId = "";
    }

    public UnitSimpleIce(String unitId, String unitName, String buildId)
    {
        this.unitId = unitId;
        this.unitName = unitName;
        this.buildId = buildId;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UnitSimpleIce _r = null;
        if(rhs instanceof UnitSimpleIce)
        {
            _r = (UnitSimpleIce)rhs;
        }

        if(_r != null)
        {
            if(unitId != _r.unitId)
            {
                if(unitId == null || _r.unitId == null || !unitId.equals(_r.unitId))
                {
                    return false;
                }
            }
            if(unitName != _r.unitName)
            {
                if(unitName == null || _r.unitName == null || !unitName.equals(_r.unitName))
                {
                    return false;
                }
            }
            if(buildId != _r.buildId)
            {
                if(buildId == null || _r.buildId == null || !buildId.equals(_r.buildId))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::spacemanage::UnitSimpleIce");
        __h = IceInternal.HashUtil.hashAdd(__h, unitId);
        __h = IceInternal.HashUtil.hashAdd(__h, unitName);
        __h = IceInternal.HashUtil.hashAdd(__h, buildId);
        return __h;
    }

    public UnitSimpleIce
    clone()
    {
        UnitSimpleIce c = null;
        try
        {
            c = (UnitSimpleIce)super.clone();
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
        __os.writeString(unitId);
        __os.writeString(unitName);
        __os.writeString(buildId);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        unitId = __is.readString();
        unitName = __is.readString();
        buildId = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UnitSimpleIce __v)
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

    static public UnitSimpleIce
    __read(IceInternal.BasicStream __is, UnitSimpleIce __v)
    {
        if(__v == null)
        {
             __v = new UnitSimpleIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UnitSimpleIce __nullMarshalValue = new UnitSimpleIce();

    public static final long serialVersionUID = -967018621L;
}
