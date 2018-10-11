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
// Generated from file `ratio_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.ratio;

public class TranstypeIce implements java.lang.Cloneable, java.io.Serializable
{
    public String drugTransTypeName;

    public String
    getDrugTransTypeName()
    {
        return drugTransTypeName;
    }

    public void
    setDrugTransTypeName(String _drugTransTypeName)
    {
        drugTransTypeName = _drugTransTypeName;
    }

    public String drugTransTypeAmount;

    public String
    getDrugTransTypeAmount()
    {
        return drugTransTypeAmount;
    }

    public void
    setDrugTransTypeAmount(String _drugTransTypeAmount)
    {
        drugTransTypeAmount = _drugTransTypeAmount;
    }

    public String sampleTransTypeName;

    public String
    getSampleTransTypeName()
    {
        return sampleTransTypeName;
    }

    public void
    setSampleTransTypeName(String _sampleTransTypeName)
    {
        sampleTransTypeName = _sampleTransTypeName;
    }

    public String sampleTransTypeAmount;

    public String
    getSampleTransTypeAmount()
    {
        return sampleTransTypeAmount;
    }

    public void
    setSampleTransTypeAmount(String _sampleTransTypeAmount)
    {
        sampleTransTypeAmount = _sampleTransTypeAmount;
    }

    public String bloodTransTypeName;

    public String
    getBloodTransTypeName()
    {
        return bloodTransTypeName;
    }

    public void
    setBloodTransTypeName(String _bloodTransTypeName)
    {
        bloodTransTypeName = _bloodTransTypeName;
    }

    public String bloodTransTypeAmount;

    public String
    getBloodTransTypeAmount()
    {
        return bloodTransTypeAmount;
    }

    public void
    setBloodTransTypeAmount(String _bloodTransTypeAmount)
    {
        bloodTransTypeAmount = _bloodTransTypeAmount;
    }

    public String patientTransTypeName;

    public String
    getPatientTransTypeName()
    {
        return patientTransTypeName;
    }

    public void
    setPatientTransTypeName(String _patientTransTypeName)
    {
        patientTransTypeName = _patientTransTypeName;
    }

    public String patientTransTypeAmount;

    public String
    getPatientTransTypeAmount()
    {
        return patientTransTypeAmount;
    }

    public void
    setPatientTransTypeAmount(String _patientTransTypeAmount)
    {
        patientTransTypeAmount = _patientTransTypeAmount;
    }

    public String goodTransTypeName;

    public String
    getGoodTransTypeName()
    {
        return goodTransTypeName;
    }

    public void
    setGoodTransTypeName(String _goodTransTypeName)
    {
        goodTransTypeName = _goodTransTypeName;
    }

    public String goodTransTypeAmount;

    public String
    getGoodTransTypeAmount()
    {
        return goodTransTypeAmount;
    }

    public void
    setGoodTransTypeAmount(String _goodTransTypeAmount)
    {
        goodTransTypeAmount = _goodTransTypeAmount;
    }

    public String bookTransTypeName;

    public String
    getBookTransTypeName()
    {
        return bookTransTypeName;
    }

    public void
    setBookTransTypeName(String _bookTransTypeName)
    {
        bookTransTypeName = _bookTransTypeName;
    }

    public String bookTransTypeAmount;

    public String
    getBookTransTypeAmount()
    {
        return bookTransTypeAmount;
    }

    public void
    setBookTransTypeAmount(String _bookTransTypeAmount)
    {
        bookTransTypeAmount = _bookTransTypeAmount;
    }

    public TranstypeIce()
    {
        drugTransTypeName = "";
        drugTransTypeAmount = "";
        sampleTransTypeName = "";
        sampleTransTypeAmount = "";
        bloodTransTypeName = "";
        bloodTransTypeAmount = "";
        patientTransTypeName = "";
        patientTransTypeAmount = "";
        goodTransTypeName = "";
        goodTransTypeAmount = "";
        bookTransTypeName = "";
        bookTransTypeAmount = "";
    }

    public TranstypeIce(String drugTransTypeName, String drugTransTypeAmount, String sampleTransTypeName, String sampleTransTypeAmount, String bloodTransTypeName, String bloodTransTypeAmount, String patientTransTypeName, String patientTransTypeAmount, String goodTransTypeName, String goodTransTypeAmount, String bookTransTypeName, String bookTransTypeAmount)
    {
        this.drugTransTypeName = drugTransTypeName;
        this.drugTransTypeAmount = drugTransTypeAmount;
        this.sampleTransTypeName = sampleTransTypeName;
        this.sampleTransTypeAmount = sampleTransTypeAmount;
        this.bloodTransTypeName = bloodTransTypeName;
        this.bloodTransTypeAmount = bloodTransTypeAmount;
        this.patientTransTypeName = patientTransTypeName;
        this.patientTransTypeAmount = patientTransTypeAmount;
        this.goodTransTypeName = goodTransTypeName;
        this.goodTransTypeAmount = goodTransTypeAmount;
        this.bookTransTypeName = bookTransTypeName;
        this.bookTransTypeAmount = bookTransTypeAmount;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TranstypeIce _r = null;
        if(rhs instanceof TranstypeIce)
        {
            _r = (TranstypeIce)rhs;
        }

        if(_r != null)
        {
            if(drugTransTypeName != _r.drugTransTypeName)
            {
                if(drugTransTypeName == null || _r.drugTransTypeName == null || !drugTransTypeName.equals(_r.drugTransTypeName))
                {
                    return false;
                }
            }
            if(drugTransTypeAmount != _r.drugTransTypeAmount)
            {
                if(drugTransTypeAmount == null || _r.drugTransTypeAmount == null || !drugTransTypeAmount.equals(_r.drugTransTypeAmount))
                {
                    return false;
                }
            }
            if(sampleTransTypeName != _r.sampleTransTypeName)
            {
                if(sampleTransTypeName == null || _r.sampleTransTypeName == null || !sampleTransTypeName.equals(_r.sampleTransTypeName))
                {
                    return false;
                }
            }
            if(sampleTransTypeAmount != _r.sampleTransTypeAmount)
            {
                if(sampleTransTypeAmount == null || _r.sampleTransTypeAmount == null || !sampleTransTypeAmount.equals(_r.sampleTransTypeAmount))
                {
                    return false;
                }
            }
            if(bloodTransTypeName != _r.bloodTransTypeName)
            {
                if(bloodTransTypeName == null || _r.bloodTransTypeName == null || !bloodTransTypeName.equals(_r.bloodTransTypeName))
                {
                    return false;
                }
            }
            if(bloodTransTypeAmount != _r.bloodTransTypeAmount)
            {
                if(bloodTransTypeAmount == null || _r.bloodTransTypeAmount == null || !bloodTransTypeAmount.equals(_r.bloodTransTypeAmount))
                {
                    return false;
                }
            }
            if(patientTransTypeName != _r.patientTransTypeName)
            {
                if(patientTransTypeName == null || _r.patientTransTypeName == null || !patientTransTypeName.equals(_r.patientTransTypeName))
                {
                    return false;
                }
            }
            if(patientTransTypeAmount != _r.patientTransTypeAmount)
            {
                if(patientTransTypeAmount == null || _r.patientTransTypeAmount == null || !patientTransTypeAmount.equals(_r.patientTransTypeAmount))
                {
                    return false;
                }
            }
            if(goodTransTypeName != _r.goodTransTypeName)
            {
                if(goodTransTypeName == null || _r.goodTransTypeName == null || !goodTransTypeName.equals(_r.goodTransTypeName))
                {
                    return false;
                }
            }
            if(goodTransTypeAmount != _r.goodTransTypeAmount)
            {
                if(goodTransTypeAmount == null || _r.goodTransTypeAmount == null || !goodTransTypeAmount.equals(_r.goodTransTypeAmount))
                {
                    return false;
                }
            }
            if(bookTransTypeName != _r.bookTransTypeName)
            {
                if(bookTransTypeName == null || _r.bookTransTypeName == null || !bookTransTypeName.equals(_r.bookTransTypeName))
                {
                    return false;
                }
            }
            if(bookTransTypeAmount != _r.bookTransTypeAmount)
            {
                if(bookTransTypeAmount == null || _r.bookTransTypeAmount == null || !bookTransTypeAmount.equals(_r.bookTransTypeAmount))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::ratio::TranstypeIce");
        __h = IceInternal.HashUtil.hashAdd(__h, drugTransTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, drugTransTypeAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, sampleTransTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, sampleTransTypeAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, bloodTransTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, bloodTransTypeAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, patientTransTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, patientTransTypeAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, goodTransTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, goodTransTypeAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, bookTransTypeName);
        __h = IceInternal.HashUtil.hashAdd(__h, bookTransTypeAmount);
        return __h;
    }

    public TranstypeIce
    clone()
    {
        TranstypeIce c = null;
        try
        {
            c = (TranstypeIce)super.clone();
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
        __os.writeString(drugTransTypeName);
        __os.writeString(drugTransTypeAmount);
        __os.writeString(sampleTransTypeName);
        __os.writeString(sampleTransTypeAmount);
        __os.writeString(bloodTransTypeName);
        __os.writeString(bloodTransTypeAmount);
        __os.writeString(patientTransTypeName);
        __os.writeString(patientTransTypeAmount);
        __os.writeString(goodTransTypeName);
        __os.writeString(goodTransTypeAmount);
        __os.writeString(bookTransTypeName);
        __os.writeString(bookTransTypeAmount);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        drugTransTypeName = __is.readString();
        drugTransTypeAmount = __is.readString();
        sampleTransTypeName = __is.readString();
        sampleTransTypeAmount = __is.readString();
        bloodTransTypeName = __is.readString();
        bloodTransTypeAmount = __is.readString();
        patientTransTypeName = __is.readString();
        patientTransTypeAmount = __is.readString();
        goodTransTypeName = __is.readString();
        goodTransTypeAmount = __is.readString();
        bookTransTypeName = __is.readString();
        bookTransTypeAmount = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TranstypeIce __v)
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

    static public TranstypeIce
    __read(IceInternal.BasicStream __is, TranstypeIce __v)
    {
        if(__v == null)
        {
             __v = new TranstypeIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TranstypeIce __nullMarshalValue = new TranstypeIce();

    public static final long serialVersionUID = -1560659712L;
}
