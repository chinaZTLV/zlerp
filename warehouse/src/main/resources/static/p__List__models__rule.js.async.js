(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[12],{"0dPK":function(a,e,t){"use strict";t.r(e);var n=t("p0pE"),r=t.n(n),c=t("d6i3"),s=t.n(c),u=t("dCQc");e["default"]={namespace:"rule",state:{data:{list:[],pagination:{}}},effects:{queryConsumer:s.a.mark(function a(e,t){var n,r,c,p;return s.a.wrap(function(a){while(1)switch(a.prev=a.next){case 0:return n=e.payload,r=t.call,c=t.put,a.next=4,r(u["o"],n);case 4:return p=a.sent,a.next=7,c({type:"saveList",payload:p});case 7:case"end":return a.stop()}},a)}),addOrUpdateConsumer:s.a.mark(function a(e,t){var n,r,c;return s.a.wrap(function(a){while(1)switch(a.prev=a.next){case 0:return n=e.payload,e.callback,r=t.call,t.put,a.next=4,r(u["b"],n);case 4:return c=a.sent,a.abrupt("return",c.header);case 6:case"end":return a.stop()}},a)}),deleteConsumer:s.a.mark(function a(e,t){var n,r,c;return s.a.wrap(function(a){while(1)switch(a.prev=a.next){case 0:return n=e.payload,e.callback,r=t.call,t.put,a.next=4,r(u["d"],n);case 4:return c=a.sent,a.abrupt("return",c.header);case 6:case"end":return a.stop()}},a)}),update:s.a.mark(function a(e,t){var n,r,c,p,o;return s.a.wrap(function(a){while(1)switch(a.prev=a.next){case 0:return n=e.payload,r=e.callback,c=t.call,p=t.put,a.next=4,c(u["B"],n);case 4:return o=a.sent,a.next=7,p({type:"save",payload:o});case 7:r&&r();case 8:case"end":return a.stop()}},a)})},reducers:{save:function(a,e){return r()({},a,{data:e.payload})},saveList:function(a,e){return r()({},a,{data:{list:e.payload.body,pagination:{current:e.payload.pageIndex,pageSize:e.payload.pageSize,total:e.payload.totalCount}}})}}}}}]);