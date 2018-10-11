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

public class TransProfileIce implements java.lang.Cloneable, java.io.Serializable
{
    public String transUserAmountAverage;

    public String
    getTransUserAmountAverage()
    {
        return transUserAmountAverage;
    }

    public void
    setTransUserAmountAverage(String _transUserAmountAverage)
    {
        transUserAmountAverage = _transUserAmountAverage;
    }

    public String transAmountTotal;

    public String
    getTransAmountTotal()
    {
        return transAmountTotal;
    }

    public void
    setTransAmountTotal(String _transAmountTotal)
    {
        transAmountTotal = _transAmountTotal;
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

    public String instantTaskTimeAverage;

    public String
    getInstantTaskTimeAverage()
    {
        return instantTaskTimeAverage;
    }

    public void
    setInstantTaskTimeAverage(String _instantTaskTimeAverage)
    {
        instantTaskTimeAverage = _instantTaskTimeAverage;
    }

    public String timelyRatio;

    public String
    getTimelyRatio()
    {
        return timelyRatio;
    }

    public void
    setTimelyRatio(String _timelyRatio)
    {
        timelyRatio = _timelyRatio;
    }

    public String satisfactionRatio;

    public String
    getSatisfactionRatio()
    {
        return satisfactionRatio;
    }

    public void
    setSatisfactionRatio(String _satisfactionRatio)
    {
        satisfactionRatio = _satisfactionRatio;
    }

    public TransProfileIce()
    {
        transUserAmountAverage = "";
        transAmountTotal = "";
        transAmountAverage = "";
        instantTaskTimeAverage = "";
        timelyRatio = "";
        satisfactionRatio = "";
    }

    public TransProfileIce(String transUserAmountAverage, String transAmountTotal, String transAmountAverage, String instantTaskTimeAverage, String timelyRatio, String satisfactionRatio)
    {
        this.transUserAmountAverage = transUserAmountAverage;
        this.transAmountTotal = transAmountTotal;
        this.transAmountAverage = transAmountAverage;
        this.instantTaskTimeAverage = instantTaskTimeAverage;
        this.timelyRatio = timelyRatio;
        this.satisfactionRatio = satisfactionRatio;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransProfileIce _r = null;
        if(rhs instanceof TransProfileIce)
        {
            _r = (TransProfileIce)rhs;
        }

        if(_r != null)
        {
            if(transUserAmountAverage != _r.transUserAmountAverage)
            {
                if(transUserAmountAverage == null || _r.transUserAmountAverage == null || !transUserAmountAverage.equals(_r.transUserAmountAverage))
                {
                    return false;
                }
            }
            if(transAmountTotal != _r.transAmountTotal)
            {
                if(transAmountTotal == null || _r.transAmountTotal == null || !transAmountTotal.equals(_r.transAmountTotal))
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
            if(instantTaskTimeAverage != _r.instantTaskTimeAverage)
            {
                if(instantTaskTimeAverage == null || _r.instantTaskTimeAverage == null || !instantTaskTimeAverage.equals(_r.instantTaskTimeAverage))
                {
                    return false;
                }
            }
            if(timelyRatio != _r.timelyRatio)
            {
                if(timelyRatio == null || _r.timelyRatio == null || !timelyRatio.equals(_r.timelyRatio))
                {
                    return false;
                }
            }
            if(satisfactionRatio != _r.satisfactionRatio)
            {
                if(satisfactionRatio == null || _r.satisfactionRatio == null || !satisfactionRatio.equals(_r.satisfactionRatio))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::organmonth::TransProfileIce");
        __h = IceInternal.HashUtil.hashAdd(__h, transUserAmountAverage);
        __h = IceInternal.HashUtil.hashAdd(__h, transAmountTotal);
        __h = IceInternal.HashUtil.hashAdd(__h, transAmountAverage);
        __h = IceInternal.HashUtil.hashAdd(__h, instantTaskTimeAverage);
        __h = IceInternal.HashUtil.hashAdd(__h, timelyRatio);
        __h = IceInternal.HashUtil.hashAdd(__h, satisfactionRatio);
        return __h;
    }

    public TransProfileIce
    clone()
    {
        TransProfileIce c = null;
        try
        {
            c = (TransProfileIce)super.clone();
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
        __os.writeString(transUserAmountAverage);
        __os.writeString(transAmountTotal);
        __os.writeString(transAmountAverage);
        __os.writeString(instantTaskTimeAverage);
        __os.writeString(timelyRatio);
        __os.writeString(satisfactionRatio);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        transUserAmountAverage = __is.readString();
        transAmountTotal = __is.readString();
        transAmountAverage = __is.readString();
        instantTaskTimeAverage = __is.readString();
        timelyRatio = __is.readString();
        satisfactionRatio = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TransProfileIce __v)
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

    static public TransProfileIce
    __read(IceInternal.BasicStream __is, TransProfileIce __v)
    {
        if(__v == null)
        {
             __v = new TransProfileIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransProfileIce __nullMarshalValue = new TransProfileIce();

    public static final long serialVersionUID = 1408296246L;
}
