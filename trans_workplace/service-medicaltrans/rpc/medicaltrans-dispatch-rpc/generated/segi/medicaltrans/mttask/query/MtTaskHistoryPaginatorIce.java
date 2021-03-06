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
// Generated from file `mt_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.query;

public class MtTaskHistoryPaginatorIce implements java.lang.Cloneable, java.io.Serializable
{
    public String code;

    public String
    getCode()
    {
        return code;
    }

    public void
    setCode(String _code)
    {
        code = _code;
    }

    public String message;

    public String
    getMessage()
    {
        return message;
    }

    public void
    setMessage(String _message)
    {
        message = _message;
    }

    public page.RpcPageIce paginator;

    public page.RpcPageIce
    getPaginator()
    {
        return paginator;
    }

    public void
    setPaginator(page.RpcPageIce _paginator)
    {
        paginator = _paginator;
    }

    public java.util.List<MtTaskHistoryRerurnIce> resultList;

    public java.util.List<MtTaskHistoryRerurnIce>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<MtTaskHistoryRerurnIce> _resultList)
    {
        resultList = _resultList;
    }

    public MtTaskHistoryPaginatorIce()
    {
        code = "";
        message = "";
    }

    public MtTaskHistoryPaginatorIce(String code, String message, page.RpcPageIce paginator, java.util.List<MtTaskHistoryRerurnIce> resultList)
    {
        this.code = code;
        this.message = message;
        this.paginator = paginator;
        this.resultList = resultList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        MtTaskHistoryPaginatorIce _r = null;
        if(rhs instanceof MtTaskHistoryPaginatorIce)
        {
            _r = (MtTaskHistoryPaginatorIce)rhs;
        }

        if(_r != null)
        {
            if(code != _r.code)
            {
                if(code == null || _r.code == null || !code.equals(_r.code))
                {
                    return false;
                }
            }
            if(message != _r.message)
            {
                if(message == null || _r.message == null || !message.equals(_r.message))
                {
                    return false;
                }
            }
            if(paginator != _r.paginator)
            {
                if(paginator == null || _r.paginator == null || !paginator.equals(_r.paginator))
                {
                    return false;
                }
            }
            if(resultList != _r.resultList)
            {
                if(resultList == null || _r.resultList == null || !resultList.equals(_r.resultList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mttask::query::MtTaskHistoryPaginatorIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, paginator);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public MtTaskHistoryPaginatorIce
    clone()
    {
        MtTaskHistoryPaginatorIce c = null;
        try
        {
            c = (MtTaskHistoryPaginatorIce)super.clone();
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
        __os.writeString(code);
        __os.writeString(message);
        __os.writeObject(paginator);
        HistoryListHelper.write(__os, resultList);
    }

    private class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            if(v == null || v instanceof page.RpcPageIce)
            {
                paginator = (page.RpcPageIce)v;
            }
            else
            {
                IceInternal.Ex.throwUOE(type(), v);
            }
        }

        public String
        type()
        {
            return "::page::RpcPageIce";
        }
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        __is.readObject(new Patcher());
        resultList = HistoryListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, MtTaskHistoryPaginatorIce __v)
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

    static public MtTaskHistoryPaginatorIce
    __read(IceInternal.BasicStream __is, MtTaskHistoryPaginatorIce __v)
    {
        if(__v == null)
        {
             __v = new MtTaskHistoryPaginatorIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final MtTaskHistoryPaginatorIce __nullMarshalValue = new MtTaskHistoryPaginatorIce();

    public static final long serialVersionUID = -51503064L;
}
