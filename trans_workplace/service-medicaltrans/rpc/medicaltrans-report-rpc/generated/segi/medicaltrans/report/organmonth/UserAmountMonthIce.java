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
// Generated from file `organMonthAmount.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.organmonth;

public class UserAmountMonthIce implements java.lang.Cloneable, java.io.Serializable
{
    public String cycleYm;

    public String
    getCycleYm()
    {
        return cycleYm;
    }

    public void
    setCycleYm(String _cycleYm)
    {
        cycleYm = _cycleYm;
    }

    public String transAmountAverage;

    public String
    getTransAmountAverage()
    {
        return transAmountAverage;
    }

    public void
    setTransAmountAverage(String _transAmountAverage)
    {
        transAmountAverage = _transAmountAverage;
    }

    public UserAmountMonthIce()
    {
        cycleYm = "";
        transAmountAverage = "";
    }

    public UserAmountMonthIce(String cycleYm, String transAmountAverage)
    {
        this.cycleYm = cycleYm;
        this.transAmountAverage = transAmountAverage;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserAmountMonthIce _r = null;
        if(rhs instanceof UserAmountMonthIce)
        {
            _r = (UserAmountMonthIce)rhs;
        }

        if(_r != null)
        {
            if(cycleYm != _r.cycleYm)
            {
                if(cycleYm == null || _r.cycleYm == null || !cycleYm.equals(_r.cycleYm))
                {
                    return false;
                }
            }
            if(transAmountAverage != _r.transAmountAverage)
            {
                if(transAmountAverage == null || _r.transAmountAverage == null || !transAmountAverage.equals(_r.transAmountAverage))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::organmonth::UserAmountMonthIce");
        __h = IceInternal.HashUtil.hashAdd(__h, cycleYm);
        __h = IceInternal.HashUtil.hashAdd(__h, transAmountAverage);
        return __h;
    }

    public UserAmountMonthIce
    clone()
    {
        UserAmountMonthIce c = null;
        try
        {
            c = (UserAmountMonthIce)super.clone();
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
        __os.writeString(cycleYm);
        __os.writeString(transAmountAverage);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        cycleYm = __is.readString();
        transAmountAverage = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserAmountMonthIce __v)
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

    static public UserAmountMonthIce
    __read(IceInternal.BasicStream __is, UserAmountMonthIce __v)
    {
        if(__v == null)
        {
             __v = new UserAmountMonthIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserAmountMonthIce __nullMarshalValue = new UserAmountMonthIce();

    public static final long serialVersionUID = 1816480218L;
}
