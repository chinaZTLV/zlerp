(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[19],{"4Ofr":function(e,t,n){e.exports={themeColor:"antd-pro-components-setting-drawer-theme-color-themeColor",title:"antd-pro-components-setting-drawer-theme-color-title",colorBlock:"antd-pro-components-setting-drawer-theme-color-colorBlock"}},BFsb:function(e,t,n){e.exports={content:"antd-pro-components-setting-drawer-index-content",blockChecbox:"antd-pro-components-setting-drawer-index-blockChecbox",item:"antd-pro-components-setting-drawer-index-item",selectIcon:"antd-pro-components-setting-drawer-index-selectIcon",color_block:"antd-pro-components-setting-drawer-index-color_block",title:"antd-pro-components-setting-drawer-index-title",handle:"antd-pro-components-setting-drawer-index-handle",productionHint:"antd-pro-components-setting-drawer-index-productionHint"}},Ctgt:function(e,t,n){"use strict";n.r(t);var o=n("q1tI"),a=n.n(o),r=n("17x9"),i=n.n(r),c=n("VCL8");function l(){return l=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var o in n)Object.prototype.hasOwnProperty.call(n,o)&&(e[o]=n[o])}return e},l.apply(this,arguments)}function s(e,t){if(null==e)return{};var n,o,a=u(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(o=0;o<r.length;o++)n=r[o],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}function u(e,t){if(null==e)return{};var n,o,a={},r=Object.keys(e);for(o=0;o<r.length;o++)n=r[o],t.indexOf(n)>=0||(a[n]=e[n]);return a}function p(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function d(e,t){for(var n=0;n<t.length;n++){var o=t[n];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(e,o.key,o)}}function f(e,t,n){return t&&d(e.prototype,t),n&&d(e,n),e}function h(e,t){return!t||"object"!==typeof t&&"function"!==typeof t?b(e):t}function m(e){return m=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)},m(e)}function g(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&y(e,t)}function y(e,t){return y=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e},y(e,t)}function b(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}function v(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var k=n("TSYQ"),C=function(e){function t(e){var n;p(this,t),n=h(this,m(t).call(this,e)),v(b(b(n)),"handleClick",function(e){var t=n.state.checked,o=n.props.onClick,a=!t;n.setChecked(a,e),o&&o(a,e)}),v(b(b(n)),"handleKeyDown",function(e){37===e.keyCode?n.setChecked(!1,e):39===e.keyCode&&n.setChecked(!0,e)}),v(b(b(n)),"handleMouseUp",function(e){var t=n.props.onMouseUp;n.node&&n.node.blur(),t&&t(e)}),v(b(b(n)),"saveNode",function(e){n.node=e});var o=!1;return o="checked"in e?!!e.checked:!!e.defaultChecked,n.state={checked:o},n}return g(t,e),f(t,[{key:"componentDidMount",value:function(){var e=this.props,t=e.autoFocus,n=e.disabled;t&&!n&&this.focus()}},{key:"setChecked",value:function(e,t){var n=this.props,o=n.disabled,a=n.onChange;o||("checked"in this.props||this.setState({checked:e}),a&&a(e,t))}},{key:"focus",value:function(){this.node.focus()}},{key:"blur",value:function(){this.node.blur()}},{key:"render",value:function(){var e,t=this.props,n=t.className,o=t.prefixCls,r=t.disabled,i=t.loadingIcon,c=t.checkedChildren,u=t.unCheckedChildren,p=s(t,["className","prefixCls","disabled","loadingIcon","checkedChildren","unCheckedChildren"]),d=this.state.checked,f=k((e={},v(e,n,!!n),v(e,o,!0),v(e,"".concat(o,"-checked"),d),v(e,"".concat(o,"-disabled"),r),e));return a.a.createElement("button",l({},p,{type:"button",role:"switch","aria-checked":d,disabled:r,className:f,ref:this.saveNode,onKeyDown:this.handleKeyDown,onClick:this.handleClick,onMouseUp:this.handleMouseUp}),i,a.a.createElement("span",{className:"".concat(o,"-inner")},d?c:u))}}],[{key:"getDerivedStateFromProps",value:function(e){var t={},n=e.checked;return"checked"in e&&(t.checked=!!n),t}}]),t}(o["Component"]);C.propTypes={className:i.a.string,prefixCls:i.a.string,disabled:i.a.bool,checkedChildren:i.a.any,unCheckedChildren:i.a.any,onChange:i.a.func,onMouseUp:i.a.func,onClick:i.a.func,tabIndex:i.a.number,checked:i.a.bool,defaultChecked:i.a.bool,autoFocus:i.a.bool,loadingIcon:i.a.node},C.defaultProps={prefixCls:"rc-switch",checkedChildren:null,unCheckedChildren:null,className:"",defaultChecked:!1},Object(c["polyfill"])(C),t["default"]=C},P5Jw:function(e,t,n){"use strict";var o=n("rHrb"),a=o.CopyToClipboard;a.CopyToClipboard=a,e.exports=a},PceP:function(e,t,n){"use strict";n.r(t);n("bbsP");var o=n("/wGt"),a=(n("fOrg"),n("+KLJ")),r=(n("+L6B"),n("2/Rp")),i=(n("miYZ"),n("tsqr")),c=(n("/zsF"),n("PArb")),l=(n("Pwec"),n("CtXQ")),s=(n("5Dmo"),n("3S7+")),u=(n("Mwp2"),n("VXEj")),p=(n("cIOH"),n("czTT"),n("q1tI")),d=n.n(p),f=n("17x9"),h=n("XIdC"),m=n.n(h),g=n("TSYQ"),y=n.n(g),b=n("BGR+"),v=n("g0mS"),k=n("H84U"),C=n("6CfX");function w(e){return w="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"===typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e},w(e)}function O(){return O=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var o in n)Object.prototype.hasOwnProperty.call(n,o)&&(e[o]=n[o])}return e},O.apply(this,arguments)}function j(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function E(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function S(e,t){for(var n=0;n<t.length;n++){var o=t[n];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(e,o.key,o)}}function x(e,t,n){return t&&S(e.prototype,t),n&&S(e,n),e}function P(e,t){return!t||"object"!==w(t)&&"function"!==typeof t?M(e):t}function M(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}function _(e){return _=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)},_(e)}function N(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&T(e,t)}function T(e,t){return T=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e},T(e,t)}var I=function(e){function t(e){var n;return E(this,t),n=P(this,_(t).call(this,e)),n.saveSwitch=function(e){n.rcSwitch=e},n.renderSwitch=function(e){var t,o=e.getPrefixCls,a=n.props,r=a.prefixCls,i=a.size,c=a.loading,s=a.className,u=void 0===s?"":s,d=a.disabled,f=o("switch",r),h=y()(u,(t={},j(t,"".concat(f,"-small"),"small"===i),j(t,"".concat(f,"-loading"),c),t)),g=c?p["createElement"](l["a"],{type:"loading",className:"".concat(f,"-loading-icon")}):null;return p["createElement"](v["a"],{insertExtraNode:!0},p["createElement"](m.a,O({},Object(b["a"])(n.props,["loading"]),{prefixCls:f,className:h,disabled:d||c,ref:n.saveSwitch,loadingIcon:g})))},Object(C["a"])("checked"in e||!("value"in e),"Switch","`value` is not validate prop, do you mean `checked`?"),n}return N(t,e),x(t,[{key:"focus",value:function(){this.rcSwitch.focus()}},{key:"blur",value:function(){this.rcSwitch.blur()}},{key:"render",value:function(){return p["createElement"](k["a"],null,this.renderSwitch)}}]),t}(p["Component"]);I.__ANT_SWITCH=!0,I.propTypes={prefixCls:f["string"],size:f["oneOf"](["small","default","large"]),className:f["string"]};var z,H,F,D=n("2Taf"),L=n.n(D),A=n("vZ4D"),B=n.n(A),R=n("l4Ni"),q=n.n(R),J=n("ujKo"),W=n.n(J),Y=n("MhPg"),K=n.n(Y),Q=n("p0pE"),U=n.n(Q),X=(n("OaEy"),n("2fM7")),Z=n("Y2fQ"),G=n("P5Jw"),V=n("MuoO"),$=n("BFsb"),ee=n.n($),te=n("jehZ"),ne=n.n(te),oe=n("Y/ft"),ae=n.n(oe),re=n("4Ofr"),ie=n.n(re),ce=function(e){var t=e.color,n=e.check,o=ae()(e,["color","check"]);return d.a.createElement("div",ne()({},o,{style:{backgroundColor:t}}),n?d.a.createElement(l["a"],{type:"check"}):"")},le=function(e){var t=e.colors,n=e.title,o=e.value,a=e.onChange,r=t;return t||(r=[{key:"dust",color:"#F5222D"},{key:"volcano",color:"#FA541C"},{key:"sunset",color:"#FAAD14"},{key:"cyan",color:"#13C2C2"},{key:"green",color:"#52C41A"},{key:"daybreak",color:"#1890FF"},{key:"geekblue",color:"#2F54EB"},{key:"purple",color:"#722ED1"}]),d.a.createElement("div",{className:ie.a.themeColor},d.a.createElement("h3",{className:ie.a.title},n),d.a.createElement("div",{className:ie.a.content},r.map(function(e){var t=e.key,n=e.color;return d.a.createElement(s["a"],{key:n,title:Object(Z["formatMessage"])({id:"app.setting.themecolor.".concat(t)})},d.a.createElement(ce,{className:ie.a.colorBlock,color:n,check:o===n,onClick:function(){return a&&a(n)}}))})))},se=le,ue=function(e){var t=e.value,n=e.onChange,o=e.list;return d.a.createElement("div",{className:ee.a.blockChecbox,key:t},o.map(function(e){return d.a.createElement(s["a"],{title:e.title,key:e.key},d.a.createElement("div",{className:ee.a.item,onClick:function(){return n(e.key)}},d.a.createElement("img",{src:e.url,alt:e.key}),d.a.createElement("div",{className:ee.a.selectIcon,style:{display:t===e.key?"block":"none"}},d.a.createElement(l["a"],{type:"check"}))))}))},pe=ue,de=X["a"].Option,fe=function(e){var t=e.children,n=e.title,o=e.style;return d.a.createElement("div",{style:U()({},o,{marginBottom:24})},d.a.createElement("h3",{className:ee.a.title},n),t)},he=(z=Object(V["connect"])(function(e){var t=e.setting;return{setting:t}}),z((F=function(e){function t(){var e,n;L()(this,t);for(var o=arguments.length,a=new Array(o),r=0;r<o;r++)a[r]=arguments[r];return n=q()(this,(e=W()(t)).call.apply(e,[this].concat(a))),n.state={collapse:!1},n.getLayoutSetting=function(){var e=n.props.setting,t=e.contentWidth,o=e.fixedHeader,a=e.layout,r=e.autoHideHeader,i=e.fixSiderbar;return[{title:Object(Z["formatMessage"])({id:"app.setting.content-width"}),action:d.a.createElement(X["a"],{value:t,size:"small",onSelect:function(e){return n.changeSetting("contentWidth",e)},style:{width:80}},"sidemenu"===a?null:d.a.createElement(de,{value:"Fixed"},Object(Z["formatMessage"])({id:"app.setting.content-width.fixed"})),d.a.createElement(de,{value:"Fluid"},Object(Z["formatMessage"])({id:"app.setting.content-width.fluid"})))},{title:Object(Z["formatMessage"])({id:"app.setting.fixedheader"}),action:d.a.createElement(I,{size:"small",checked:!!o,onChange:function(e){return n.changeSetting("fixedHeader",e)}})},{title:Object(Z["formatMessage"])({id:"app.setting.hideheader"}),disabled:!o,disabledReason:Object(Z["formatMessage"])({id:"app.setting.hideheader.hint"}),action:d.a.createElement(I,{size:"small",checked:!!r,onChange:function(e){return n.changeSetting("autoHideHeader",e)}})},{title:Object(Z["formatMessage"])({id:"app.setting.fixedsidebar"}),disabled:"topmenu"===a,disabledReason:Object(Z["formatMessage"])({id:"app.setting.fixedsidebar.hint"}),action:d.a.createElement(I,{size:"small",checked:!!i,onChange:function(e){return n.changeSetting("fixSiderbar",e)}})}]},n.changeSetting=function(e,t){var o=n.props.setting,a=U()({},o);a[e]=t,"layout"===e?a.contentWidth="topmenu"===t?"Fixed":"Fluid":"fixedHeader"!==e||t||(a.autoHideHeader=!1),n.setState(a,function(){var e=n.props.dispatch;e({type:"setting/changeSetting",payload:n.state})})},n.togglerContent=function(){var e=n.state.collapse;n.setState({collapse:!e})},n.renderLayoutSettingItem=function(e){var t=d.a.cloneElement(e.action,{disabled:e.disabled});return d.a.createElement(s["a"],{title:e.disabled?e.disabledReason:"",placement:"left"},d.a.createElement(u["a"].Item,{actions:[t]},d.a.createElement("span",{style:{opacity:e.disabled?"0.5":""}},e.title)))},n}return K()(t,e),B()(t,[{key:"render",value:function(){var e=this,t=this.props.setting,n=t.navTheme,s=t.primaryColor,p=t.layout,f=t.colorWeak,h=this.state.collapse;return d.a.createElement(o["a"],{visible:h,width:300,onClose:this.togglerContent,placement:"right",handler:d.a.createElement("div",{className:ee.a.handle,onClick:this.togglerContent},d.a.createElement(l["a"],{type:h?"close":"setting",style:{color:"#fff",fontSize:20}})),style:{zIndex:999}},d.a.createElement("div",{className:ee.a.content},d.a.createElement(fe,{title:Object(Z["formatMessage"])({id:"app.setting.pagestyle"})},d.a.createElement(pe,{list:[{key:"dark",url:"https://gw.alipayobjects.com/zos/rmsportal/LCkqqYNmvBEbokSDscrm.svg",title:Object(Z["formatMessage"])({id:"app.setting.pagestyle.dark"})},{key:"light",url:"https://gw.alipayobjects.com/zos/rmsportal/jpRkZQMyYRryryPNtyIC.svg",title:Object(Z["formatMessage"])({id:"app.setting.pagestyle.light"})}],value:n,onChange:function(t){return e.changeSetting("navTheme",t)}})),d.a.createElement(se,{title:Object(Z["formatMessage"])({id:"app.setting.themecolor"}),value:s,onChange:function(t){return e.changeSetting("primaryColor",t)}}),d.a.createElement(c["a"],null),d.a.createElement(fe,{title:Object(Z["formatMessage"])({id:"app.setting.navigationmode"})},d.a.createElement(pe,{list:[{key:"sidemenu",url:"https://gw.alipayobjects.com/zos/rmsportal/JopDzEhOqwOjeNTXkoje.svg",title:Object(Z["formatMessage"])({id:"app.setting.sidemenu"})},{key:"topmenu",url:"https://gw.alipayobjects.com/zos/rmsportal/KDNDBbriJhLwuqMoxcAr.svg",title:Object(Z["formatMessage"])({id:"app.setting.topmenu"})}],value:p,onChange:function(t){return e.changeSetting("layout",t)}})),d.a.createElement(u["a"],{split:!1,dataSource:this.getLayoutSetting(),renderItem:this.renderLayoutSettingItem}),d.a.createElement(c["a"],null),d.a.createElement(fe,{title:Object(Z["formatMessage"])({id:"app.setting.othersettings"})},d.a.createElement(u["a"],{split:!1,renderItem:this.renderLayoutSettingItem,dataSource:[{title:Object(Z["formatMessage"])({id:"app.setting.weakmode"}),action:d.a.createElement(I,{size:"small",checked:!!f,onChange:function(t){return e.changeSetting("colorWeak",t)}})}]})),d.a.createElement(c["a"],null),d.a.createElement(G["CopyToClipboard"],{text:JSON.stringify(Object(b["a"])(t,["colorWeak"]),null,2),onCopy:function(){return i["a"].success(Object(Z["formatMessage"])({id:"app.setting.copyinfo"}))}},d.a.createElement(r["a"],{block:!0,icon:"copy"},Object(Z["formatMessage"])({id:"app.setting.copy"}))),d.a.createElement(a["a"],{type:"warning",className:ee.a.productionHint,message:d.a.createElement("div",null,Object(Z["formatMessage"])({id:"app.setting.production.hint"})," ",d.a.createElement("a",{href:"https://u.ant.design/pro-v2-default-settings",target:"_blank",rel:"noopener noreferrer"},"src/defaultSettings.js"))})))}}]),t}(p["Component"]),H=F))||H);t["default"]=he},XIdC:function(e,t,n){e.exports=n("Ctgt")},czTT:function(e,t,n){e.exports={"ant-switch":"ant-switch","ant-switch-inner":"ant-switch-inner","ant-switch-loading-icon":"ant-switch-loading-icon","ant-switch-disabled":"ant-switch-disabled","ant-switch-loading":"ant-switch-loading","ant-switch-checked":"ant-switch-checked","ant-switch-small":"ant-switch-small",AntSwitchSmallLoadingCircle:"AntSwitchSmallLoadingCircle"}},rHrb:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.CopyToClipboard=void 0;var o=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var o in n)Object.prototype.hasOwnProperty.call(n,o)&&(e[o]=n[o])}return e},a=function(){function e(e,t){for(var n=0;n<t.length;n++){var o=t[n];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(e,o.key,o)}}return function(t,n,o){return n&&e(t.prototype,n),o&&e(t,o),t}}(),r=n("q1tI"),i=s(r),c=n("+QRC"),l=s(c);function s(e){return e&&e.__esModule?e:{default:e}}function u(e,t){var n={};for(var o in e)t.indexOf(o)>=0||Object.prototype.hasOwnProperty.call(e,o)&&(n[o]=e[o]);return n}function p(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function d(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!==typeof t&&"function"!==typeof t?e:t}function f(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var h=t.CopyToClipboard=function(e){function t(){var e,n,o,a;p(this,t);for(var r=arguments.length,c=Array(r),s=0;s<r;s++)c[s]=arguments[s];return o=d(this,(e=t.__proto__||Object.getPrototypeOf(t)).call.apply(e,[this].concat(c))),n=o,o.onClick=function(e){var t=o.props,n=t.text,a=t.onCopy,r=t.children,c=t.options,s=i.default.Children.only(r),u=(0,l.default)(n,c);a&&a(n,u),s&&s.props&&"function"===typeof s.props.onClick&&s.props.onClick(e)},a=n,d(o,a)}return f(t,e),a(t,[{key:"render",value:function(){var e=this.props,t=(e.text,e.onCopy,e.options,e.children),n=u(e,["text","onCopy","options","children"]),a=i.default.Children.only(t);return i.default.cloneElement(a,o({},n,{onClick:this.onClick}))}}]),t}(i.default.PureComponent);h.defaultProps={onCopy:void 0,options:void 0}}}]);