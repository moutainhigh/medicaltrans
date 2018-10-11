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
// Generated from file `mt_trans_statistics.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.trans;

public class TransRatioIce implements java.lang.Cloneable, java.io.Serializable
{
    public String amountName;

    public String
    getAmountName()
    {
        return amountName;
    }

    public void
    setAmountName(String _amountName)
    {
        amountName = _amountName;
    }

    public String amount;

    public String
    getAmount()
    {
        return amount;
    }

    public void
    setAmount(String _amount)
    {
        amount = _amount;
    }

    public TransRatioIce()
    {
        amountName = "";
        amount = "";
    }

    public TransRatioIce(String amountName, String amount)
    {
        this.amountName = amountName;
        this.amount = amount;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransRatioIce _r = null;
        if(rhs instanceof TransRatioIce)
        {
            _r = (TransRatioIce)rhs;
        }

        if(_r != null)
        {
            if(amountName != _r.amountName)
            {
                if(amountName == null || _r.amountName == null || !amountName.equals(_r.amountName))
                {
                    return false;
                }
            }
            if(amount != _r.amount)
            {
                if(amount == null || _r.amount == null || !amount.equals(_r.amount))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::trans::TransRatioIce");
        __h = IceInternal.HashUtil.hashAdd(__h, amountName);
        __h = IceInternal.HashUtil.hashAdd(__h, amount);
        return __h;
    }

    public TransRatioIce
    clone()
    {
        TransRatioIce c = null;
        try
        {
            c = (TransRatioIce)super.clone();
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
        __os.writeString(amountName);
        __os.writeString(amount);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        amountName = __is.readString();
        amount = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TransRatioIce __v)
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

    static public TransRatioIce
    __read(IceInternal.BasicStream __is, TransRatioIce __v)
    {
        if(__v == null)
        {
             __v = new TransRatioIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransRatioIce __nullMarshalValue = new TransRatioIce();

    public static final long serialVersionUID = 652750437L;
}
