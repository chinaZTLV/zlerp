(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[13],{QBEj:function(e,t,a){"use strict";a.r(t);var n=a("jehZ"),r=a.n(n),o=(a("IzEo"),a("bx4M")),i=(a("14J3"),a("BMrR")),s=(a("+L6B"),a("2/Rp")),l=(a("jCWc"),a("kPKH")),u=(a("miYZ"),a("tsqr")),p=(a("/zsF"),a("PArb")),c=(a("P2fV"),a("NJEC")),d=a("p0pE"),h=a.n(d),m=a("2Taf"),f=a.n(m),v=a("vZ4D"),y=a.n(v),b=a("l4Ni"),g=a.n(b),E=a("ujKo"),w=a.n(E),C=a("MhPg"),S=a.n(C),x=(a("2qtc"),a("kLXV")),M=(a("cIOH"),a("QbM5"),a("q1tI")),N=a.n(M),V=a("TSYQ"),O=a.n(V),T=a("jo6Y"),P=a.n(T),D=a("QbLZ"),I=a.n(D),F=a("iCc5"),k=a.n(F),U=a("FYw3"),A=a.n(U),K=a("mRg0"),R=a.n(K),q=a("17x9"),L=a.n(q),j=a("4IlW"),B=a("V7oC"),H=a.n(B),z=function(e){function t(){k()(this,t);var e=A()(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments));return e.state={active:!1},e.onTouchStart=function(t){e.triggerEvent("TouchStart",!0,t)},e.onTouchMove=function(t){e.triggerEvent("TouchMove",!1,t)},e.onTouchEnd=function(t){e.triggerEvent("TouchEnd",!1,t)},e.onTouchCancel=function(t){e.triggerEvent("TouchCancel",!1,t)},e.onMouseDown=function(t){e.triggerEvent("MouseDown",!0,t)},e.onMouseUp=function(t){e.triggerEvent("MouseUp",!1,t)},e.onMouseLeave=function(t){e.triggerEvent("MouseLeave",!1,t)},e}return R()(t,e),H()(t,[{key:"componentDidUpdate",value:function(){this.props.disabled&&this.state.active&&this.setState({active:!1})}},{key:"triggerEvent",value:function(e,t,a){var n="on"+e,r=this.props.children;r.props[n]&&r.props[n](a),t!==this.state.active&&this.setState({active:t})}},{key:"render",value:function(){var e=this.props,t=e.children,a=e.disabled,n=e.activeClassName,r=e.activeStyle,o=a?void 0:{onTouchStart:this.onTouchStart,onTouchMove:this.onTouchMove,onTouchEnd:this.onTouchEnd,onTouchCancel:this.onTouchCancel,onMouseDown:this.onMouseDown,onMouseUp:this.onMouseUp,onMouseLeave:this.onMouseLeave},i=N.a.Children.only(t);if(!a&&this.state.active){var s=i.props,l=s.style,u=s.className;return!1!==r&&(r&&(l=I()({},l,r)),u=O()(u,n)),N.a.cloneElement(i,I()({className:u,style:l},o))}return N.a.cloneElement(i,o)}}]),t}(N.a.Component),_=z;z.defaultProps={disabled:!1};var Y=function(e){function t(){return k()(this,t),A()(this,e.apply(this,arguments))}return R()(t,e),t.prototype.render=function(){var e=this.props,t=e.prefixCls,a=e.disabled,n=P()(e,["prefixCls","disabled"]);return N.a.createElement(_,{disabled:a,activeClassName:t+"-handler-active"},N.a.createElement("span",n))},t}(M["Component"]);Y.propTypes={prefixCls:L.a.string,disabled:L.a.bool,onTouchStart:L.a.func,onTouchEnd:L.a.func,onMouseDown:L.a.func,onMouseUp:L.a.func,onMouseLeave:L.a.func};var Q=Y;function W(){}function J(e){e.preventDefault()}function Z(e){return e.replace(/[^\w\.-]+/g,"")}var X=200,G=600,$=Number.MAX_SAFE_INTEGER||Math.pow(2,53)-1,ee=function(e){return void 0!==e&&null!==e},te=function(e){function t(a){k()(this,t);var n=A()(this,e.call(this,a));ae.call(n);var r=void 0;r="value"in a?a.value:a.defaultValue,n.state={focused:a.autoFocus};var o=n.getValidValue(n.toNumber(r));return n.state=I()({},n.state,{inputValue:n.toPrecisionAsStep(o),value:o}),n}return R()(t,e),t.prototype.componentDidMount=function(){this.componentDidUpdate()},t.prototype.componentDidUpdate=function(e){var t=this.props,a=t.value,n=t.onChange,r=t.max,o=t.min,i=this.state.focused;if(e){if(e.value!==a||e.max!==r||e.min!==o){var s=i?a:this.getValidValue(a),l=void 0;l=this.pressingUpOrDown?s:this.inputting?this.rawInput:this.toPrecisionAsStep(s),this.setState({value:s,inputValue:l})}var u="value"in this.props?a:this.state.value;"max"in this.props&&e.max!==r&&"number"===typeof u&&u>r&&n&&n(r),"min"in this.props&&e.min!==o&&"number"===typeof u&&u<o&&n&&n(o)}try{if(void 0!==this.cursorStart&&this.state.focused)if(this.partRestoreByAfter(this.cursorAfter)||this.state.value===this.props.value){if(this.currentValue===this.input.value)switch(this.lastKeyCode){case j["a"].BACKSPACE:this.fixCaret(this.cursorStart-1,this.cursorStart-1);break;case j["a"].DELETE:this.fixCaret(this.cursorStart+1,this.cursorStart+1);break;default:}}else{var p=this.cursorStart+1;this.cursorAfter?this.lastKeyCode===j["a"].BACKSPACE?p=this.cursorStart-1:this.lastKeyCode===j["a"].DELETE&&(p=this.cursorStart):p=this.input.value.length,this.fixCaret(p,p)}}catch(e){}this.lastKeyCode=null,this.pressingUpOrDown&&(this.props.focusOnUpDown&&this.state.focused&&document.activeElement!==this.input&&this.focus(),this.pressingUpOrDown=!1)},t.prototype.componentWillUnmount=function(){this.stop()},t.prototype.getCurrentValidValue=function(e){var t=e;return t=""===t?"":this.isNotCompleteNumber(parseFloat(t,10))?this.state.value:this.getValidValue(t),this.toNumber(t)},t.prototype.getRatio=function(e){var t=1;return e.metaKey||e.ctrlKey?t=.1:e.shiftKey&&(t=10),t},t.prototype.getValueFromEvent=function(e){var t=e.target.value.trim().replace(/\u3002/g,".");return ee(this.props.decimalSeparator)&&(t=t.replace(this.props.decimalSeparator,".")),t},t.prototype.getValidValue=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:this.props.min,a=arguments.length>2&&void 0!==arguments[2]?arguments[2]:this.props.max,n=parseFloat(e,10);return isNaN(n)?e:(n<t&&(n=t),n>a&&(n=a),n)},t.prototype.setValue=function(e,t){var a=this.props.precision,n=this.isNotCompleteNumber(parseFloat(e,10))?null:parseFloat(e,10),r=this.state,o=r.value,i=void 0===o?null:o,s=r.inputValue,l=void 0===s?null:s,u="number"===typeof n?n.toFixed(a):""+n,p=n!==i||u!==""+l;return"value"in this.props?this.setState({inputValue:this.toPrecisionAsStep(this.state.value)},t):this.setState({value:n,inputValue:this.toPrecisionAsStep(e)},t),p&&this.props.onChange(n),n},t.prototype.getPrecision=function(e){if(ee(this.props.precision))return this.props.precision;var t=e.toString();if(t.indexOf("e-")>=0)return parseInt(t.slice(t.indexOf("e-")+2),10);var a=0;return t.indexOf(".")>=0&&(a=t.length-t.indexOf(".")-1),a},t.prototype.getMaxPrecision=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,a=this.props,n=a.precision,r=a.step;if(ee(n))return n;var o=this.getPrecision(t),i=this.getPrecision(r),s=this.getPrecision(e);return e?Math.max(s,o+i):o+i},t.prototype.getPrecisionFactor=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,a=this.getMaxPrecision(e,t);return Math.pow(10,a)},t.prototype.fixCaret=function(e,t){if(void 0!==e&&void 0!==t&&this.input&&this.input.value)try{var a=this.input.selectionStart,n=this.input.selectionEnd;e===a&&t===n||this.input.setSelectionRange(e,t)}catch(e){}},t.prototype.focus=function(){this.input.focus(),this.recordCursorPosition()},t.prototype.blur=function(){this.input.blur()},t.prototype.formatWrapper=function(e){return this.props.formatter?this.props.formatter(e):e},t.prototype.toPrecisionAsStep=function(e){if(this.isNotCompleteNumber(e)||""===e)return e;var t=Math.abs(this.getMaxPrecision(e));return isNaN(t)?e.toString():Number(e).toFixed(t)},t.prototype.isNotCompleteNumber=function(e){return isNaN(e)||""===e||null===e||e&&e.toString().indexOf(".")===e.toString().length-1},t.prototype.toNumber=function(e){var t=this.props.precision,a=this.state.focused,n=e&&e.length>16&&a;return this.isNotCompleteNumber(e)||n?e:ee(t)?Math.round(e*Math.pow(10,t))/Math.pow(10,t):Number(e)},t.prototype.upStep=function(e,t){var a=this.props.step,n=this.getPrecisionFactor(e,t),r=Math.abs(this.getMaxPrecision(e,t)),o=((n*e+n*a*t)/n).toFixed(r);return this.toNumber(o)},t.prototype.downStep=function(e,t){var a=this.props.step,n=this.getPrecisionFactor(e,t),r=Math.abs(this.getMaxPrecision(e,t)),o=((n*e-n*a*t)/n).toFixed(r);return this.toNumber(o)},t.prototype.step=function(e,t){var a=this,n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:1,r=arguments[3];this.stop(),t&&(t.persist(),t.preventDefault());var o=this.props;if(!o.disabled){var i=this.getCurrentValidValue(this.state.inputValue)||0;if(!this.isNotCompleteNumber(i)){var s=this[e+"Step"](i,n),l=s>o.max||s<o.min;s>o.max?s=o.max:s<o.min&&(s=o.min),this.setValue(s),this.setState({focused:!0}),l||(this.autoStepTimer=setTimeout(function(){a[e](t,n,!0)},r?X:G))}}},t.prototype.render=function(){var e,t=I()({},this.props),a=t.prefixCls,n=t.disabled,r=t.readOnly,o=t.useTouch,i=t.autoComplete,s=t.upHandler,l=t.downHandler,u=(P()(t,["prefixCls","disabled","readOnly","useTouch","autoComplete","upHandler","downHandler"]),O()((e={},e[a]=!0,e[t.className]=!!t.className,e[a+"-disabled"]=n,e[a+"-focused"]=this.state.focused,e))),p="",c="",d=this.state.value;if(d||0===d)if(isNaN(d))p=a+"-handler-up-disabled",c=a+"-handler-down-disabled";else{var h=Number(d);h>=t.max&&(p=a+"-handler-up-disabled"),h<=t.min&&(c=a+"-handler-down-disabled")}var m={};for(var f in t)!t.hasOwnProperty(f)||"data-"!==f.substr(0,5)&&"aria-"!==f.substr(0,5)&&"role"!==f||(m[f]=t[f]);var v=!t.readOnly&&!t.disabled,y=this.getInputDisplayValue(),b=void 0,g=void 0;o?(b={onTouchStart:v&&!p?this.up:W,onTouchEnd:this.stop},g={onTouchStart:v&&!c?this.down:W,onTouchEnd:this.stop}):(b={onMouseDown:v&&!p?this.up:W,onMouseUp:this.stop,onMouseLeave:this.stop},g={onMouseDown:v&&!c?this.down:W,onMouseUp:this.stop,onMouseLeave:this.stop});var E=!!p||n||r,w=!!c||n||r;return N.a.createElement("div",{className:u,style:t.style,title:t.title,onMouseEnter:t.onMouseEnter,onMouseLeave:t.onMouseLeave,onMouseOver:t.onMouseOver,onMouseOut:t.onMouseOut},N.a.createElement("div",{className:a+"-handler-wrap"},N.a.createElement(Q,I()({ref:this.saveUp,disabled:E,prefixCls:a,unselectable:"unselectable"},b,{role:"button","aria-label":"Increase Value","aria-disabled":!!E,className:a+"-handler "+a+"-handler-up "+p}),s||N.a.createElement("span",{unselectable:"unselectable",className:a+"-handler-up-inner",onClick:J})),N.a.createElement(Q,I()({ref:this.saveDown,disabled:w,prefixCls:a,unselectable:"unselectable"},g,{role:"button","aria-label":"Decrease Value","aria-disabled":!!w,className:a+"-handler "+a+"-handler-down "+c}),l||N.a.createElement("span",{unselectable:"unselectable",className:a+"-handler-down-inner",onClick:J}))),N.a.createElement("div",{className:a+"-input-wrap"},N.a.createElement("input",I()({role:"spinbutton","aria-valuemin":t.min,"aria-valuemax":t.max,"aria-valuenow":d,required:t.required,type:t.type,placeholder:t.placeholder,onClick:t.onClick,onMouseUp:this.onMouseUp,className:a+"-input",tabIndex:t.tabIndex,autoComplete:i,onFocus:this.onFocus,onBlur:this.onBlur,onKeyDown:v?this.onKeyDown:W,onKeyUp:v?this.onKeyUp:W,autoFocus:t.autoFocus,maxLength:t.maxLength,readOnly:t.readOnly,disabled:t.disabled,max:t.max,min:t.min,step:t.step,name:t.name,id:t.id,onChange:this.onChange,ref:this.saveInput,value:y,pattern:t.pattern},m))))},t}(N.a.Component);te.propTypes={value:L.a.oneOfType([L.a.number,L.a.string]),defaultValue:L.a.oneOfType([L.a.number,L.a.string]),focusOnUpDown:L.a.bool,autoFocus:L.a.bool,onChange:L.a.func,onPressEnter:L.a.func,onKeyDown:L.a.func,onKeyUp:L.a.func,prefixCls:L.a.string,tabIndex:L.a.oneOfType([L.a.string,L.a.number]),disabled:L.a.bool,onFocus:L.a.func,onBlur:L.a.func,readOnly:L.a.bool,max:L.a.number,min:L.a.number,step:L.a.oneOfType([L.a.number,L.a.string]),upHandler:L.a.node,downHandler:L.a.node,useTouch:L.a.bool,formatter:L.a.func,parser:L.a.func,onMouseEnter:L.a.func,onMouseLeave:L.a.func,onMouseOver:L.a.func,onMouseOut:L.a.func,onMouseUp:L.a.func,precision:L.a.number,required:L.a.bool,pattern:L.a.string,decimalSeparator:L.a.string},te.defaultProps={focusOnUpDown:!0,useTouch:!1,prefixCls:"rc-input-number",min:-$,step:1,style:{},onChange:W,onKeyDown:W,onPressEnter:W,onFocus:W,onBlur:W,parser:Z,required:!1,autoComplete:"off"};var ae=function(){var e=this;this.onKeyDown=function(t){for(var a=arguments.length,n=Array(a>1?a-1:0),r=1;r<a;r++)n[r-1]=arguments[r];var o=e.props,i=o.onKeyDown,s=o.onPressEnter;if(t.keyCode===j["a"].UP){var l=e.getRatio(t);e.up(t,l),e.stop()}else if(t.keyCode===j["a"].DOWN){var u=e.getRatio(t);e.down(t,u),e.stop()}else t.keyCode===j["a"].ENTER&&s&&s(t);e.recordCursorPosition(),e.lastKeyCode=t.keyCode,i&&i.apply(void 0,[t].concat(n))},this.onKeyUp=function(t){for(var a=arguments.length,n=Array(a>1?a-1:0),r=1;r<a;r++)n[r-1]=arguments[r];var o=e.props.onKeyUp;e.stop(),e.recordCursorPosition(),o&&o.apply(void 0,[t].concat(n))},this.onChange=function(t){var a=e.props.onChange;e.state.focused&&(e.inputting=!0),e.rawInput=e.props.parser(e.getValueFromEvent(t)),e.setState({inputValue:e.rawInput}),a(e.toNumber(e.rawInput))},this.onMouseUp=function(){var t=e.props.onMouseUp;e.recordCursorPosition(),t&&t.apply(void 0,arguments)},this.onFocus=function(){var t;e.setState({focused:!0}),(t=e.props).onFocus.apply(t,arguments)},this.onBlur=function(t){for(var a=arguments.length,n=Array(a>1?a-1:0),r=1;r<a;r++)n[r-1]=arguments[r];var o=e.props.onBlur;e.inputting=!1,e.setState({focused:!1});var i=e.getCurrentValidValue(e.state.inputValue);t.persist();var s=e.setValue(i);if(o){var l=e.input.value,u=e.getInputDisplayValue({focus:!1,value:s});e.input.value=u,o.apply(void 0,[t].concat(n)),e.input.value=l}},this.getInputDisplayValue=function(t){var a=t||e.state,n=a.focused,r=a.inputValue,o=a.value,i=void 0;i=n?r:e.toPrecisionAsStep(o),void 0!==i&&null!==i||(i="");var s=e.formatWrapper(i);return ee(e.props.decimalSeparator)&&(s=s.toString().replace(".",e.props.decimalSeparator)),s},this.recordCursorPosition=function(){try{e.cursorStart=e.input.selectionStart,e.cursorEnd=e.input.selectionEnd,e.currentValue=e.input.value,e.cursorBefore=e.input.value.substring(0,e.cursorStart),e.cursorAfter=e.input.value.substring(e.cursorEnd)}catch(e){}},this.restoreByAfter=function(t){if(void 0===t)return!1;var a=e.input.value,n=a.lastIndexOf(t);return-1!==n&&(n+t.length===a.length&&(e.fixCaret(n,n),!0))},this.partRestoreByAfter=function(t){return void 0!==t&&Array.prototype.some.call(t,function(a,n){var r=t.substring(n);return e.restoreByAfter(r)})},this.stop=function(){e.autoStepTimer&&clearTimeout(e.autoStepTimer)},this.down=function(t,a,n){e.pressingUpOrDown=!0,e.step("down",t,a,n)},this.up=function(t,a,n){e.pressingUpOrDown=!0,e.step("up",t,a,n)},this.saveUp=function(t){e.upHandler=t},this.saveDown=function(t){e.downHandler=t},this.saveInput=function(t){e.input=t}},ne=te,re=a("CtXQ"),oe=a("H84U");function ie(e){return ie="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"===typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e},ie(e)}function se(){return se=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e},se.apply(this,arguments)}function le(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function ue(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function pe(e,t){for(var a=0;a<t.length;a++){var n=t[a];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function ce(e,t,a){return t&&pe(e.prototype,t),a&&pe(e,a),e}function de(e,t){return!t||"object"!==ie(t)&&"function"!==typeof t?he(e):t}function he(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}function me(e){return me=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)},me(e)}function fe(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&ve(e,t)}function ve(e,t){return ve=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e},ve(e,t)}var ye=function(e,t){var a={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&t.indexOf(n)<0&&(a[n]=e[n]);if(null!=e&&"function"===typeof Object.getOwnPropertySymbols){var r=0;for(n=Object.getOwnPropertySymbols(e);r<n.length;r++)t.indexOf(n[r])<0&&Object.prototype.propertyIsEnumerable.call(e,n[r])&&(a[n[r]]=e[n[r]])}return a},be=function(e){function t(){var e;return ue(this,t),e=de(this,me(t).apply(this,arguments)),e.saveInputNumber=function(t){e.inputNumberRef=t},e.renderInputNumber=function(t){var a,n=t.getPrefixCls,r=e.props,o=r.className,i=r.size,s=r.prefixCls,l=ye(r,["className","size","prefixCls"]),u=n("input-number",s),p=O()((a={},le(a,"".concat(u,"-lg"),"large"===i),le(a,"".concat(u,"-sm"),"small"===i),a),o),c=M["createElement"](re["a"],{type:"up",className:"".concat(u,"-handler-up-inner")}),d=M["createElement"](re["a"],{type:"down",className:"".concat(u,"-handler-down-inner")});return M["createElement"](ne,se({ref:e.saveInputNumber,className:p,upHandler:c,downHandler:d,prefixCls:u},l))},e}return fe(t,e),ce(t,[{key:"focus",value:function(){this.inputNumberRef.focus()}},{key:"blur",value:function(){this.inputNumberRef.blur()}},{key:"render",value:function(){return M["createElement"](oe["a"],null,this.renderInputNumber)}}]),t}(M["Component"]);be.defaultProps={step:1};a("OaEy");var ge,Ee,we,Ce,Se,xe,Me,Ne,Ve=a("2fM7"),Oe=(a("5NDa"),a("5rEg")),Te=(a("y8nQ"),a("Vl3Y")),Pe=a("MuoO"),De=a("wd/R"),Ie=a.n(De),Fe=a("CkN6"),ke=a("zHco"),Ue=(a("7ntv"),a("z8EN")),Ae=a.n(Ue),Ke=Te["a"].Item,Re=Oe["a"].TextArea,qe=Ve["a"].Option,Le=Te["a"].create()(function(e){var t=e.modalVisible,a=e.form,n=e.handleAdd,r=e.handleModalVisible,o=e.consumerNames,i=e.materialTypes,s=e.isShow,l=e.isShowMaterial,u=function(){a.validateFields(function(e,t){e||(a.resetFields(),t.discount=void 0==t.discount?"100":t.discount,n(t))})},p=function(e){l("2"==e||"3"==e?"block":"none")};return N.a.createElement(x["a"],{destroyOnClose:!0,title:"\u4e0b\u5355",visible:t,onOk:u,onCancel:function(){return r()}},console.log("materialTypes",i),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u4ea4\u6613\u7c7b\u578b"},a.getFieldDecorator("manageType",{rules:[{required:!0,message:"\u8bf7\u9009\u62e9\u4ea4\u6613\u7c7b\u578b"}]})(N.a.createElement(Ve["a"],{style:{width:"100%"},onChange:p},N.a.createElement(qe,{value:"0"},"\u9000\u8fd8\u5382\u65b9"),N.a.createElement(qe,{value:"1"},"\u8fdb\u8d27"),N.a.createElement(qe,{value:"2"},"\u552e\u8d27"),N.a.createElement(qe,{value:"3"},"\u9000\u8d27")))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u7269\u6599\u7c7b\u578b"},a.getFieldDecorator("productKindId",{rules:[{required:!0,message:"\u8bf7\u9009\u62e9\u7269\u6599\u7c7b\u578b"}]})(N.a.createElement(Ve["a"],{style:{width:"100%"}},i&&i.length>0?i.map(function(e){return N.a.createElement(qe,{key:e.key,value:e.key},e.value)}):""))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u6570\u91cf"},a.getFieldDecorator("stockNum",{rules:[{required:!0,pattern:new RegExp(/^[1-9]\d*$/,"g"),message:"\u8bf7\u8f93\u5165\u6574\u6570\u6570\u91cf"}]})(N.a.createElement(be,{style:{width:"100%"}}))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u6298\u6263\uff08%\uff09",style:{display:s}},a.getFieldDecorator("discount",{})(N.a.createElement(be,{defaultValue:100,style:{width:"100%"}}))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u5ba2\u6237\u540d\u79f0",style:{display:s}},a.getFieldDecorator("consumerId",{})(N.a.createElement(Ve["a"],{style:{width:"100%"}},o&&o.length>0?o.map(function(e){return N.a.createElement(qe,{key:e.key,value:e.key},e.value)}):""))))}),je=(ge=Te["a"].create(),ge((Ce=we=function(e){function t(e){var a;return f()(this,t),a=g()(this,w()(t).call(this,e)),a.okHandle=function(){form.validateFields(function(e,t){e||(form.resetFields(),handleAdd(t))})},a.handleNext=function(e){var t=a.props,n=t.form,r=t.handleUpdate,o=a.state.formVals;n.validateFields(function(t,n){if(!t){var i=h()({},o,n);a.setState({formVals:i},function(){e<2?a.forward():r(i)})}})},a.backward=function(){var e=a.state.currentStep;a.setState({currentStep:e-1})},a.forward=function(){var e=a.state.currentStep;a.setState({currentStep:e+1})},a.renderContent=function(e,t){var n=a.props.form;return[N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u7269\u6599\u540d\u79f0"},n.getFieldDecorator("productKindName",{initialValue:t.productKindName,rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u6700\u591a20\u4e2a\u5b57\u7b26\u7684\u7269\u6599\u540d\u79f0\uff01",max:20}]})(N.a.createElement(Oe["a"],{placeholder:"\u8bf7\u8f93\u5165"}))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u5ba2\u6237\u540d\u79f0"},n.getFieldDecorator("consumerId",{initialValue:t.consumerId,rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u6700\u591a20\u4e2a\u5b57\u7b26\u7684\u5ba2\u6237\u540d\u79f0\uff01",max:20}]})(N.a.createElement(Ve["a"],{style:{width:"100%"}},N.a.createElement(qe,{value:"1"},"\u5c0f\u800d\u82b1\u67aa\u57fa\u672c\u539f\u5219\u57fa\u672c\u539f\u5219"),N.a.createElement(qe,{value:"2"},"\u5c0f\u800d\u82b1\u67aa\u57fa\u672c\u539f\u521911"),N.a.createElement(qe,{value:"3"},"\u5c0f\u800d\u82b1\u67aa\u57fa\u672c\u539f\u521922")))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u7269\u6599\u8fdb\u4ef7\uff08\u5143\uff09"},n.getFieldDecorator("purchasePrice",{initialValue:t.purchasePrice,rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u6700\u591a8\u4e2a\u5b57\u7b26\u7684\u7269\u6599\u8fdb\u4ef7\uff01",max:8}]})(N.a.createElement(Oe["a"],{placeholder:"\u8bf7\u8f93\u5165"}))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u7269\u6599\u552e\u4ef7\uff08\u5143\uff09"},n.getFieldDecorator("sellingPrice",{initialValue:t.sellingPrice,rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u6700\u591a8\u4e2a\u5b57\u7b26\u7684\u7269\u6599\u552e\u4ef7\uff01",max:8}]})(N.a.createElement(Oe["a"],{placeholder:"\u8bf7\u8f93\u5165"}))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u552e\u8d27\u5355\u4f4d"},n.getFieldDecorator("unit",{initialValue:"\u5757"==t.unit?"0":"\u4e2a"==t.unit?"1":"\u7c73"==t.unit?"2":"3",rules:[{required:!0,message:"\u8bf7\u9009\u62e9\u552e\u8d27\u5355\u4f4d\uff01",max:5}]})(N.a.createElement(Ve["a"],{style:{width:"100%"}},N.a.createElement(qe,{value:"0"},"\u5757"),N.a.createElement(qe,{value:"1"},"\u4e2a"),N.a.createElement(qe,{value:"2"},"\u7c73"),N.a.createElement(qe,{value:"3"},"\u5e73\u65b9")))),N.a.createElement(Ke,{labelCol:{span:5},wrapperCol:{span:15},label:"\u5907\u6ce8\u4fe1\u606f"},n.getFieldDecorator("remark",{initialValue:t.remark,rules:[{message:"\u5907\u6ce8\u4fe1\u606f\u6700\u591a\u53ef\u8f93\u516560\u5b57\u7b26\uff01",max:60}]})(N.a.createElement(Re,{rows:4})))]},a.state={formVals:{consumerId:e.values.consumerId,consumerIds:e.values.consumerIds,consumerName:e.values.consumerName,createTime:e.values.createTime,productKindId:e.values.productKindId,productKindName:e.values.productKindName,purchasePrice:e.values.purchasePrice,remark:e.values.remark,sellingPrice:e.values.sellingPrice,unit:e.values.unit,updateTime:e.values.updateTime},currentStep:0},a.formLayout={labelCol:{span:7},wrapperCol:{span:13}},a}return S()(t,e),y()(t,[{key:"render",value:function(){var e=this,t=this.props,a=t.updateModalVisible,n=t.handleUpdateModalVisible,r=t.values,o=t.form,i=t.handleUpdate,s=this.state,l=s.currentStep,u=s.formVals,p=function(){var t=e.state.formVals;o.validateFields(function(a,n){if(console.log("fieldsValue",n),!a){var r=h()({},t,n);e.setState({formVals:r}),i(r)}})};return N.a.createElement(x["a"],{width:640,bodyStyle:{padding:"32px 40px 48px"},destroyOnClose:!0,title:"\u4fee\u6539",visible:a,onCancel:function(){return n(!1,r)},afterClose:function(){return n()},onOk:p},this.renderContent(l,u))}}]),t}(M["PureComponent"]),we.defaultProps={handleUpdate:function(){},handleUpdateModalVisible:function(){},values:{}},Ee=Ce))||Ee),Be=(Se=Object(Pe["connect"])(function(e){var t=e.order,a=e.loading;return{order:t,loading:a.models.order}}),xe=Te["a"].create(),Se(Me=xe((Ne=function(e){function t(){var e,a;f()(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return a=g()(this,(e=w()(t)).call.apply(e,[this].concat(r))),a.state={modalVisible:!1,updateModalVisible:!1,expandForm:!1,selectedRows:[],formValues:{},stepFormValues:{},consumerNames:[],materialTypes:[],isShow:"none"},a.columns=[{title:"\u8ba2\u5355\u7f16\u53f7 ",dataIndex:"orderId"},{title:"\u4ea4\u6613\u72b6\u6001 ",dataIndex:"tradeType"},{title:"\u4ea4\u6613\u7c7b\u578b ",dataIndex:"manageType"},{title:"\u7269\u6599\u540d\u79f0 ",dataIndex:"productKindName"},{title:"\u6570\u91cf ",dataIndex:"stockNum"},{title:"\u5355\u4f4d ",dataIndex:"unit"},{title:"\u5355\u4ef7(\u5143) ",dataIndex:"unitPrice"},{title:"\u6298\u6263\uff08%\uff09 ",dataIndex:"discount"},{title:"\u6298\u6263\u91d1\u989d(\u5143) ",dataIndex:"discountAmount"},{title:"\u7269\u6599\u8fdb\u4ef7(\u5143) ",dataIndex:"purchasePrice"},{title:"\u603b\u91d1\u989d(\u5143) ",dataIndex:"totalAmount"},{title:"\u5229\u6da6(\u5143) ",dataIndex:"netReceipt"},{title:"\u5ba2\u6237\u540d\u79f0 ",dataIndex:"consumerName"},{title:"\u8ba2\u5355\u521b\u5efa\u65f6\u95f4 ",dataIndex:"createTime",render:function(e){return N.a.createElement("span",null,Ie()(e).format("YYYY-MM-DD HH:mm:ss"))}},{title:"\u64cd\u4f5c",render:function(e,t){return N.a.createElement(M["Fragment"],null,"\u5df2\u4e0b\u5355"==t.tradeType?N.a.createElement(c["a"],{title:"\u786e\u5b9a\u53d1\u8d27\u5417\uff1f",onConfirm:function(){return a.doDelivered(t)}},N.a.createElement("a",null,"\u53d1\u8d27")):"\u5df2\u53d1\u8d27"==t.tradeType?N.a.createElement(c["a"],{title:"\u786e\u5b9a\u652f\u4ed8\u5417\uff1f",onConfirm:function(){return a.doPayment(t)}},N.a.createElement("a",null,"\u652f\u4ed8")):"","\u5df2\u4e0b\u5355"==t.tradeType||"\u5df2\u53d1\u8d27"==t.tradeType?N.a.createElement(p["a"],{type:"vertical"}):"",N.a.createElement(c["a"],{title:"\u786e\u5b9a\u5220\u9664\u5417\uff1f",onConfirm:function(){return a.deleteInfo(t)}},N.a.createElement("a",null,"\u5220\u9664")))}}],a.isShowMaterial=function(e){a.setState({isShow:e})},a.handleStandardTableChange=function(e){var t=a.props.dispatch;t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:e.current,pageSize:e.pageSize}})},a.handleFormReset=function(){var e=a.props,t=e.form,n=e.dispatch;t.resetFields(),a.setState({formValues:{}}),n({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}})},a.handleMenuClick=function(e){var t=a.props.dispatch,n=a.state.selectedRows;if(0!==n.length)switch(e.key){case"remove":t({type:"order/remove",payload:{key:n.map(function(e){return e.key})},callback:function(){a.setState({selectedRows:[]})}});break;default:break}},a.handleSelectRows=function(e){a.setState({selectedRows:e})},a.handleSearch=function(e){e.preventDefault();var t=a.props,n=t.dispatch,r=t.form;r.validateFields(function(e,t){if(!e){var r=h()({},t);a.setState({formValues:r}),console.log("values",r),n({type:"order/queryOrder",payload:{body:r,header:{},pageIndex:1,pageSize:10}})}})},a.handleModalVisible=function(e){a.setState({modalVisible:!!e})},a.handleUpdateModalVisible=function(e,t){a.setState({updateModalVisible:!!e,stepFormValues:t||{}})},a.deleteInfo=function(e){var t=a.props.dispatch;t({type:"order/deleteOrder",payload:{body:e,header:{}}}).then(function(e){console.log("res",e),""!=e&&null!=e&&void 0!=e?"0"==e.resultCode?(u["a"].success("\u5220\u9664\u6210\u529f"),a.handleModalVisible(),t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}})):u["a"].error(e.resultDesc):u["a"].error("\u64cd\u4f5c\u5931\u8d25")})},a.doDelivered=function(e){var t=a.props.dispatch;t({type:"order/doDelivered",payload:{body:e,header:{}}}).then(function(e){""!=e&&null!=e&&void 0!=e?"0"==e.resultCode?(u["a"].success("\u64cd\u4f5c\u6210\u529f"),a.handleModalVisible(),t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}})):u["a"].error(e.resultDesc):u["a"].error("\u64cd\u4f5c\u5931\u8d25")})},a.doPayment=function(e){var t=a.props.dispatch;t({type:"order/doPayment",payload:{body:e,header:{}}}).then(function(e){console.log("res",e),""!=e&&null!=e&&void 0!=e?"0"==e.resultCode?(u["a"].success("\u64cd\u4f5c\u6210\u529f"),a.handleModalVisible(),t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}})):u["a"].error(e.resultDesc):u["a"].error("\u64cd\u4f5c\u5931\u8d25")})},a.handleAdd=function(e){var t=a.props.dispatch;t({type:"order/queryPlacingAnOrder",payload:{body:e,header:{}}}).then(function(e){""!=e&&null!=e&&void 0!=e?"0"==e.resultCode?(u["a"].success("\u4e0b\u5355\u6210\u529f"),a.handleModalVisible(),t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}})):u["a"].error(e.resultDesc):u["a"].error("\u64cd\u4f5c\u5931\u8d25")})},a.handleUpdate=function(e){console.log("fields",e);var t=a.props.dispatch;t({type:"order/addOrUpdateMaterialKind",payload:{body:e,header:{}}}).then(function(e){""!=e&&null!=e&&void 0!=e?"0"==e.resultCode?(u["a"].success("\u4fee\u6539\u6210\u529f"),a.handleUpdateModalVisible(),t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}})):u["a"].error(e.resultDesc):u["a"].error("\u64cd\u4f5c\u5931\u8d25")})},a}return S()(t,e),y()(t,[{key:"componentDidMount",value:function(){var e=this,t=this.props.dispatch;t({type:"order/queryOrder",payload:{body:{},header:{},pageIndex:1,pageSize:10}}),t({type:"order/getMaterialKindListByType"}).then(function(t){e.setState({materialTypes:t.body})}),t({type:"order/queryConsumerName1"}).then(function(t){console.log("res",t),e.setState({consumerNames:t.body})})}},{key:"renderSimpleForm",value:function(){var e=this.props.form.getFieldDecorator;return N.a.createElement(Te["a"],{onSubmit:this.handleSearch,layout:"inline"},N.a.createElement(i["a"],{gutter:{md:8,lg:24,xl:48}},N.a.createElement(l["a"],{md:8,sm:24},N.a.createElement(Ke,{label:"\u7269\u6599\u540d\u79f0"},e("productKindName",{rules:[{message:"\u7269\u6599\u540d\u79f0\u6700\u591a\u53ef\u4ee5\u8f93\u516520\u4e2a\u5b57\u7b26\uff01",max:20}]})(N.a.createElement(Oe["a"],{placeholder:"\u8bf7\u8f93\u5165"})))),N.a.createElement(l["a"],{md:8,sm:24},N.a.createElement("span",{className:Ae.a.submitButtons},N.a.createElement(s["a"],{type:"primary",htmlType:"submit"},"\u67e5\u8be2"),N.a.createElement(s["a"],{style:{marginLeft:8},onClick:this.handleFormReset},"\u91cd\u7f6e")))))}},{key:"renderForm",value:function(){return this.renderSimpleForm()}},{key:"render",value:function(){var e=this,t=this.props,a=t.order.data,n=(t.loading,this.state),i=n.selectedRows,l=n.modalVisible,u=n.updateModalVisible,p=n.stepFormValues;console.log("materialTypes11",this.state.materialTypes);var c={handleAdd:this.handleAdd,handleModalVisible:this.handleModalVisible,consumerNames:this.state.consumerNames,materialTypes:this.state.materialTypes,isShow:this.state.isShow,isShowMaterial:this.isShowMaterial},d={handleUpdateModalVisible:this.handleUpdateModalVisible,handleUpdate:this.handleUpdate,consumerNames:this.state.consumerNames};return N.a.createElement(ke["a"],null,N.a.createElement(o["a"],{bordered:!1},N.a.createElement("div",{className:Ae.a.tableList},N.a.createElement("div",{className:Ae.a.tableListForm},this.renderForm()),N.a.createElement("div",{className:Ae.a.tableListOperator},N.a.createElement(s["a"],{type:"primary",onClick:function(){return e.handleModalVisible(!0)}},"\u4e0b\u5355")),N.a.createElement(Fe["a"],{selectedRows:i,data:a,columns:this.columns,onSelectRow:this.handleSelectRows,onChange:this.handleStandardTableChange}))),N.a.createElement(Le,r()({},c,{modalVisible:l})),p&&Object.keys(p).length?N.a.createElement(je,r()({},d,{updateModalVisible:u,values:p})):null)}}]),t}(M["PureComponent"]),Me=Ne))||Me)||Me);t["default"]=Be},QbM5:function(e,t,a){e.exports={"ant-input-number":"ant-input-number","ant-input-number-lg":"ant-input-number-lg","ant-input-number-sm":"ant-input-number-sm","ant-input-number-handler":"ant-input-number-handler","ant-input-number-handler-down-inner":"ant-input-number-handler-down-inner","ant-input-number-handler-up-inner":"ant-input-number-handler-up-inner","ant-input-number-handler-down-inner-icon":"ant-input-number-handler-down-inner-icon","ant-input-number-handler-up-inner-icon":"ant-input-number-handler-up-inner-icon","ant-input-number-focused":"ant-input-number-focused","ant-input-number-disabled":"ant-input-number-disabled","ant-input-number-input":"ant-input-number-input","ant-input-number-handler-wrap":"ant-input-number-handler-wrap","ant-input-number-handler-up":"ant-input-number-handler-up","ant-input-number-handler-down":"ant-input-number-handler-down","ant-input-number-handler-down-disabled":"ant-input-number-handler-down-disabled","ant-input-number-handler-up-disabled":"ant-input-number-handler-up-disabled"}}}]);