(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[10],{CAth:function(e,a,t){"use strict";t.r(a);var n=t("p0pE"),r=t.n(n),s=t("d6i3"),c=t.n(s),u=t("dCQc");a["default"]={namespace:"material",state:{data:{list:[],pagination:{}}},effects:{queryMaterialKind:c.a.mark(function e(a,t){var n,r,s,p;return c.a.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a.payload,r=t.call,s=t.put,e.next=4,r(u["u"],n);case 4:return p=e.sent,console.log("response",p),e.next=8,s({type:"saveList",payload:p});case 8:case"end":return e.stop()}},e)}),addOrUpdateMaterialKind:c.a.mark(function e(a,t){var n,r,s;return c.a.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a.payload,a.callback,r=t.call,t.put,e.next=4,r(u["c"],n);case 4:return s=e.sent,e.abrupt("return",s.header);case 6:case"end":return e.stop()}},e)}),deleteMaterialKind:c.a.mark(function e(a,t){var n,r,s;return c.a.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a.payload,a.callback,r=t.call,t.put,e.next=4,r(u["e"],n);case 4:return s=e.sent,e.abrupt("return",s.header);case 6:case"end":return e.stop()}},e)}),queryConsumerName:c.a.mark(function e(a,t){var n,r,s;return c.a.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a.payload,r=t.call,t.put,e.next=4,r(u["p"],n);case 4:return s=e.sent,console.log("response",s),e.abrupt("return",s);case 7:case"end":return e.stop()}},e)})},reducers:{save:function(e,a){return r()({},e,{data:a.payload})},saveList:function(e,a){return r()({},e,{data:{list:a.payload.body,pagination:{current:a.payload.pageIndex,pageSize:a.payload.pageSize,total:a.payload.totalCount}}})}}}}}]);