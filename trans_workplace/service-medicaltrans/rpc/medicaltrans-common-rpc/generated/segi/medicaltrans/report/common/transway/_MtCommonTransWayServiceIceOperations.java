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
// Generated from file `mt_transway_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transway;

public interface _MtCommonTransWayServiceIceOperations
{
    resp.RpcRespIce saveTransWay(TransWayInfo transWayInfo, Ice.Current __current);

    resp.RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo, Ice.Current __current);

    resp.RpcRespIce deleteTransWayById(int id, Ice.Current __current);

    TransWayInfoReturnIce getTransWayById(int id, Ice.Current __current);
}
