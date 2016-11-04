/**
 * @fileoverview �ٶȵ�ͼ�������ƹ��ߣ����⿪�š�
 * �����û��ڵ�ͼ�ϵ����������ƵĹ��ܡ�
 * ʹ���߿����Զ��������ƽ���������ʽ�������߿���ɫ�����߶ξ��롢����ȵȡ�
 * ���������<a href="symbols/BMapLib.DrawingManager.html">DrawingManager</a>��
 * ����Baidu Map API 1.4��
 *
 * @author Baidu Map Api Group 
 * @version 1.4
 */

/** 
 * @namespace BMap������library�������BMapLib�����ռ���
 */
var BMapLib = window.BMapLib = BMapLib || {};

/**
 * ���峣��, ���Ƶ�ģʽ
 * @final {Number} DrawingType
 */
var BMAP_DRAWING_MARKER    = "marker",     // ��껭��ģʽ
    BMAP_DRAWING_POLYLINE  = "polyline",   // ��껭��ģʽ
    BMAP_DRAWING_CIRCLE    = "circle",     // ��껭Բģʽ
    BMAP_DRAWING_RECTANGLE = "rectangle",  // ��껭����ģʽ
    BMAP_DRAWING_POLYGON   = "polygon";    // ��껭�����ģʽ

(function() {

    /**
     * ����baidu��
     */
    var baidu = baidu || {guid : "$BAIDU$"};
    (function() {
        // һЩҳ�漶��Ψһ�����ԣ���Ҫ������window[baidu.guid]��
        window[baidu.guid] = {};

        /**
         * ��Դ������������Կ�����Ŀ�������
         * @name baidu.extend
         * @function
         * @grammar baidu.extend(target, source)
         * @param {Object} target Ŀ�����
         * @param {Object} source Դ����
         * @returns {Object} Ŀ�����
         */
        baidu.extend = function (target, source) {
            for (var p in source) {
                if (source.hasOwnProperty(p)) {
                    target[p] = source[p];
                }
            }    
            return target;
        };

        /**
         * @ignore
         * @namespace
         * @baidu.lang �����Բ���ķ�װ�����������жϡ�ģ����չ���̳л����Լ������Զ����¼���֧�֡�
         * @property guid �����Ψһ��ʶ
         */
        baidu.lang = baidu.lang || {};

        /**
         * ����һ����ǰҳ���Ψһ��ʶ�ַ�����
         * @function
         * @grammar baidu.lang.guid()
         * @returns {String} ��ǰҳ���Ψһ��ʶ�ַ���
         */
        baidu.lang.guid = function() {
            return "TANGRAM__" + (window[baidu.guid]._counter ++).toString(36);
        };

        window[baidu.guid]._counter = window[baidu.guid]._counter || 1;

        /**
         * �������ʵ��������
         * keyΪÿ��ʵ����guid
         */
        window[baidu.guid]._instances = window[baidu.guid]._instances || {};

        /**
         * Tangram�̳л����ṩ��һ�����࣬�û�����ͨ���̳�baidu.lang.Class����ȡ�������Լ�������
         * @function
         * @name baidu.lang.Class
         * @grammar baidu.lang.Class(guid)
         * @param {string} guid	�����Ψһ��ʶ
         * @meta standard
         * @remark baidu.lang.Class�����������ʵ��������һ��ȫ��Ψһ�ı�ʶguid��
         * guid���ڹ��캯�������ɵģ���ˣ��̳���baidu.lang.Class����Ӧ��ֱ�ӻ��߼�ӵ������Ĺ��캯����<br>
         * baidu.lang.Class�Ĺ��캯���в���guid�ķ�ʽ���Ա�֤guid��Ψһ�ԣ���ÿ��ʵ������һ��ȫ��Ψһ��guid��
         */
        baidu.lang.Class = function(guid) {
            this.guid = guid || baidu.lang.guid();
            window[baidu.guid]._instances[this.guid] = this;
        };

        window[baidu.guid]._instances = window[baidu.guid]._instances || {};

        /**
         * �ж�Ŀ������Ƿ�string���ͻ�String����
         * @name baidu.lang.isString
         * @function
         * @grammar baidu.lang.isString(source)
         * @param {Any} source Ŀ�����
         * @shortcut isString
         * @meta standard
         *             
         * @returns {boolean} �����жϽ��
         */
        baidu.lang.isString = function (source) {
            return '[object String]' == Object.prototype.toString.call(source);
        };

        /**
         * �ж�Ŀ������Ƿ�Ϊfunction��Functionʵ��
         * @name baidu.lang.isFunction
         * @function
         * @grammar baidu.lang.isFunction(source)
         * @param {Any} source Ŀ�����
         * @returns {boolean} �����жϽ��
         */
        baidu.lang.isFunction = function (source) {
            return '[object Function]' == Object.prototype.toString.call(source);
        };

        /**
         * ������Ĭ�ϵ�toString������ʹ�÷�����Ϣ����׼ȷһЩ��
         * @return {string} �����String��ʾ��ʽ
         */
        baidu.lang.Class.prototype.toString = function(){
            return "[object " + (this._className || "Object" ) + "]";
        };

        /**
         * �ͷŶ��������е���Դ����Ҫ���Զ����¼���
         * @name dispose
         * @grammar obj.dispose()
         */
        baidu.lang.Class.prototype.dispose = function(){
            delete window[baidu.guid]._instances[this.guid];
            for(var property in this){
                if (!baidu.lang.isFunction(this[property])) {
                    delete this[property];
                }
            }
            this.disposed = true;
        };

        /**
         * �Զ�����¼�����
         * @function
         * @name baidu.lang.Event
         * @grammar baidu.lang.Event(type[, target])
         * @param {string} type	 �¼��������ơ�Ϊ�˷��������¼���һ����ͨ�ķ������¼��������Ʊ�����"on"(Сд)��ͷ��
         * @param {Object} [target]�����¼��Ķ���
         * @meta standard
         * @remark �����ģ�飬���Զ�ΪClass����3���¼���չ������addEventListener��removeEventListener��dispatchEvent��
         * @see baidu.lang.Class
         */
        baidu.lang.Event = function (type, target) {
            this.type = type;
            this.returnValue = true;
            this.target = target || null;
            this.currentTarget = null;
        };

        /**
         * ע�������¼�������������baidu.lang.Event��Class������ʵ���Ż��ø÷�����
         * @grammar obj.addEventListener(type, handler[, key])
         * @param 	{string}   type         �Զ����¼�������
         * @param 	{Function} handler      �Զ����¼�������ʱӦ�õ��õĻص�����
         * @param 	{string}   [key]		Ϊ�¼���������ָ�������ƣ������Ƴ�ʱʹ�á�������ṩ��������Ĭ��Ϊ������һ��ȫ��Ψһ��key��
         * @remark 	�¼��������ִ�Сд������Զ����¼����Ʋ�����Сд"on"��ͷ���÷������������"on"�ٽ����жϣ���"click"��"onclick"�ᱻ��Ϊ��ͬһ���¼��� 
         */
        baidu.lang.Class.prototype.addEventListener = function (type, handler, key) {
            if (!baidu.lang.isFunction(handler)) {
                return;
            }
            !this.__listeners && (this.__listeners = {});
            var t = this.__listeners, id;
            if (typeof key == "string" && key) {
                if (/[^\w\-]/.test(key)) {
                    throw("nonstandard key:" + key);
                } else {
                    handler.hashCode = key; 
                    id = key;
                }
            }
            type.indexOf("on") != 0 && (type = "on" + type);
            typeof t[type] != "object" && (t[type] = {});
            id = id || baidu.lang.guid();
            handler.hashCode = id;
            t[type][id] = handler;
        };
         
        /**
         * �Ƴ�������¼�������������baidu.lang.Event��Class������ʵ���Ż��ø÷�����
         * @grammar obj.removeEventListener(type, handler)
         * @param {string}   type     �¼�����
         * @param {Function|string} handler  Ҫ�Ƴ����¼������������߼���������key
         * @remark 	����ڶ�������handlerû�б��󶨵���Ӧ���Զ����¼��У�ʲôҲ������
         */
        baidu.lang.Class.prototype.removeEventListener = function (type, handler) {
            if (baidu.lang.isFunction(handler)) {
                handler = handler.hashCode;
            } else if (!baidu.lang.isString(handler)) {
                return;
            }
            !this.__listeners && (this.__listeners = {});
            type.indexOf("on") != 0 && (type = "on" + type);
            var t = this.__listeners;
            if (!t[type]) {
                return;
            }
            t[type][handler] && delete t[type][handler];
        };

        /**
         * �ɷ��Զ����¼���ʹ�ð󶨵��Զ����¼�����ĺ������ᱻִ�С�����baidu.lang.Event��Class������ʵ���Ż��ø÷�����
         * @grammar obj.dispatchEvent(event, options)
         * @param {baidu.lang.Event|String} event 	Event���󣬻��¼�����(1.1.1��֧��)
         * @param {Object} options ��չ����,�������Լ�ֵ����չ��Event������(1.2��֧��)
         * @remark ��������ͨ��addEventListenr�󶨵��Զ����¼��ص�����֮�⣬�������ֱ�Ӱ󶨵�����������Զ����¼���
         * ���磺<br>
         * myobj.onMyEvent = function(){}<br>
         * myobj.addEventListener("onMyEvent", function(){});
         */
        baidu.lang.Class.prototype.dispatchEvent = function (event, options) {
            if (baidu.lang.isString(event)) {
                event = new baidu.lang.Event(event);
            }
            !this.__listeners && (this.__listeners = {});
            options = options || {};
            for (var i in options) {
                event[i] = options[i];
            }
            var i, t = this.__listeners, p = event.type;
            event.target = event.target || this;
            event.currentTarget = this;
            p.indexOf("on") != 0 && (p = "on" + p);
            baidu.lang.isFunction(this[p]) && this[p].apply(this, arguments);
            if (typeof t[p] == "object") {
                for (i in t[p]) {
                    t[p][i].apply(this, arguments);
                }
            }
            return event.returnValue;
        };

        /**
         * Ϊ���͹����������̳й�ϵ
         * @name baidu.lang.inherits
         * @function
         * @grammar baidu.lang.inherits(subClass, superClass[, className])
         * @param {Function} subClass ���๹����
         * @param {Function} superClass ���๹����
         * @param {string} className ������ʶ
         * @remark ʹsubClass�̳�superClass��prototype��
         * ���subClass��ʵ���ܹ�ʹ��superClass��prototype�ж�����������Ժͷ�����<br>
         * �������ʵ�����ǽ�����subClass��superClass��ԭ�������ɣ�����subClass������constructor������<br>
         * <strong>ע�⣺���Ҫ�̳й��캯������Ҫ��subClass����callһ�£�����������demo����</strong>
         * @shortcut inherits
         * @meta standard
         * @see baidu.lang.Class
         */
        baidu.lang.inherits = function (subClass, superClass, className) {
            var key, proto, 
                selfProps = subClass.prototype, 
                clazz = new Function();        
            clazz.prototype = superClass.prototype;
            proto = subClass.prototype = new clazz();
            for (key in selfProps) {
                proto[key] = selfProps[key];
            }
            subClass.prototype.constructor = subClass;
            subClass.superClass = superClass.prototype;

            if ("string" == typeof className) {
                proto._className = className;
            }
        };

        /**
         * @ignore
         * @namespace baidu.dom ����dom�ķ�����
         */
        baidu.dom = baidu.dom || {};

        /**
         * ���ĵ��л�ȡָ����DOMԪ��
         * 
         * @param {string|HTMLElement} id Ԫ�ص�id��DOMԪ��
         * @meta standard
         * @return {HTMLElement} DOMԪ�أ���������ڣ�����null������������Ϸ���ֱ�ӷ��ز���
         */
        baidu._g = baidu.dom._g = function (id) {
            if (baidu.lang.isString(id)) {
                return document.getElementById(id);
            }
            return id;
        };

        /**
         * ���ĵ��л�ȡָ����DOMԪ��
         * @name baidu.dom.g
         * @function
         * @grammar baidu.dom.g(id)
         * @param {string|HTMLElement} id Ԫ�ص�id��DOMԪ��
         * @meta standard
         *             
         * @returns {HTMLElement|null} ��ȡ��Ԫ�أ����Ҳ���ʱ����null,����������Ϸ���ֱ�ӷ��ز���
         */
        baidu.g = baidu.dom.g = function (id) {
            if ('string' == typeof id || id instanceof String) {
                return document.getElementById(id);
            } else if (id && id.nodeName && (id.nodeType == 1 || id.nodeType == 9)) {
                return id;
            }
            return null;
        };

        /**
         * ��Ŀ��Ԫ�ص�ָ��λ�ò���HTML����
         * @name baidu.dom.insertHTML
         * @function
         * @grammar baidu.dom.insertHTML(element, position, html)
         * @param {HTMLElement|string} element Ŀ��Ԫ�ػ�Ŀ��Ԫ�ص�id
         * @param {string} position ����html��λ����Ϣ��ȡֵΪbeforeBegin,afterBegin,beforeEnd,afterEnd
         * @param {string} html Ҫ�����html
         * @remark
         * 
         * ����position��������Сд������<br>
         * ��������˼��beforeBegin<span>afterBegin   this is span! beforeEnd</span> afterEnd <br />
         * ���⣬���ʹ�ñ������������script��ǩ��HTML�ַ�����script��ǩ��Ӧ�Ľű������ᱻִ�С�
         * 
         * @shortcut insertHTML
         * @meta standard
         *             
         * @returns {HTMLElement} Ŀ��Ԫ��
         */
        baidu.insertHTML = baidu.dom.insertHTML = function (element, position, html) {
            element = baidu.dom.g(element);
            var range,begin;

            if (element.insertAdjacentHTML) {
                element.insertAdjacentHTML(position, html);
            } else {
                // ���ﲻ��"undefined" != typeof(HTMLElement) && !window.opera�жϣ������������������
                // ������ʵ�����жϣ�����������µ�����������Ͳ���ִ����
                range = element.ownerDocument.createRange();
                // FF��range��λ�����ô�����ܵ��´���������fragment�ڲ���dom��֮��html�ṹ�ҵ�
                // ����range.insertNode������html, by wenyuxiang @ 2010-12-14.
                position = position.toUpperCase();
                if (position == 'AFTERBEGIN' || position == 'BEFOREEND') {
                    range.selectNodeContents(element);
                    range.collapse(position == 'AFTERBEGIN');
                } else {
                    begin = position == 'BEFOREBEGIN';
                    range[begin ? 'setStartBefore' : 'setEndAfter'](element);
                    range.collapse(begin);
                }
                range.insertNode(range.createContextualFragment(html));
            }
            return element;
        };

        /**
         * ΪĿ��Ԫ�����className
         * @name baidu.dom.addClass
         * @function
         * @grammar baidu.dom.addClass(element, className)
         * @param {HTMLElement|string} element Ŀ��Ԫ�ػ�Ŀ��Ԫ�ص�id
         * @param {string} className Ҫ��ӵ�className������ͬʱ��Ӷ��class���м�ʹ�ÿհ׷��ָ�
         * @remark
         * ʹ����Ӧ��֤�ṩ��className�Ϸ��ԣ���Ӧ�������Ϸ��ַ���className�Ϸ��ַ��ο���http://www.w3.org/TR/CSS2/syndata.html��
         * @shortcut addClass
         * @meta standard
         * 	            
         * @returns {HTMLElement} Ŀ��Ԫ��
         */
        baidu.ac = baidu.dom.addClass = function (element, className) {
            element = baidu.dom.g(element);
            var classArray = className.split(/\s+/),
                result = element.className,
                classMatch = " " + result + " ",
                i = 0,
                l = classArray.length;

            for (; i < l; i++){
                 if ( classMatch.indexOf( " " + classArray[i] + " " ) < 0 ) {
                     result += (result ? ' ' : '') + classArray[i];
                 }
            }

            element.className = result;
            return element;
        };

        /**
         * @ignore
         * @namespace baidu.event ��������������Ե��¼���װ��
         * @property target 	�¼��Ĵ���Ԫ��
         * @property pageX 		����¼������x����
         * @property pageY 		����¼������y����
         * @property keyCode 	�����¼��ļ�ֵ
         */
        baidu.event = baidu.event || {};

        /**
         * �¼��������Ĵ洢��
         * @private
         * @meta standard
         */
        baidu.event._listeners = baidu.event._listeners || [];

        /**
         * ΪĿ��Ԫ������¼�������
         * @name baidu.event.on
         * @function
         * @grammar baidu.event.on(element, type, listener)
         * @param {HTMLElement|string|window} element Ŀ��Ԫ�ػ�Ŀ��Ԫ��id
         * @param {string} type �¼�����
         * @param {Function} listener ��Ҫ��ӵļ�����
         * @remark
         *  1. ��֧�ֿ���������������¼����������<br>
         *  2. �ķ�����Ϊ�����������¼������Է�ֹ��iframe�¼����ص��¼������ȡʧ��            
         * @shortcut on
         * @meta standard
         * @see baidu.event.un
         *             
         * @returns {HTMLElement|window} Ŀ��Ԫ��
         */
        baidu.on = baidu.event.on = function (element, type, listener) {
            type = type.replace(/^on/i, '');
            element = baidu._g(element);
            var realListener = function (ev) {
                // 1. ���ﲻ֧��EventArgument,  ԭ���ǿ�frame���¼�����
                // 2. element��Ϊ������this
                listener.call(element, ev);
            },
            lis = baidu.event._listeners,
            filter = baidu.event._eventFilter,
            afterFilter,
            realType = type;
            type = type.toLowerCase();
            // filter����
            if(filter && filter[type]){
                afterFilter = filter[type](element, type, realListener);
                realType = afterFilter.type;
                realListener = afterFilter.listener;
            }
            // �¼�����������
            if (element.addEventListener) {
                element.addEventListener(realType, realListener, false);
            } else if (element.attachEvent) {
                element.attachEvent('on' + realType, realListener);
            }
          
            // ���������洢��������
            lis[lis.length] = [element, type, listener, realListener, realType];
            return element;
        };

        /**
         * ΪĿ��Ԫ���Ƴ��¼�������
         * @name baidu.event.un
         * @function
         * @grammar baidu.event.un(element, type, listener)
         * @param {HTMLElement|string|window} element Ŀ��Ԫ�ػ�Ŀ��Ԫ��id
         * @param {string} type �¼�����
         * @param {Function} listener ��Ҫ�Ƴ��ļ�����
         * @shortcut un
         * @meta standard
         *             
         * @returns {HTMLElement|window} Ŀ��Ԫ��
         */
        baidu.un = baidu.event.un = function (element, type, listener) {
            element = baidu._g(element);
            type = type.replace(/^on/i, '').toLowerCase();
            
            var lis = baidu.event._listeners, 
                len = lis.length,
                isRemoveAll = !listener,
                item,
                realType, realListener;
            
            //�����listener�Ľṹ�ĳ�json
            //���Խ�ʡ�����ѭ�����Ż�����
            //��������un��ʹ��Ƶ�ʲ����ߣ�ͬʱ��listener�����ʱ��
            //����������������Ĳ���Դ������Ӱ��
            //�ݲ����Ǵ��Ż�
            while (len--) {
                item = lis[len];
                
                // listener����ʱ���Ƴ�element��������listener������type�����¼�
                // listener������ʱ���Ƴ�element������type�����¼�
                if (item[1] === type
                    && item[0] === element
                    && (isRemoveAll || item[2] === listener)) {
                    realType = item[4];
                    realListener = item[3];
                    if (element.removeEventListener) {
                        element.removeEventListener(realType, realListener, false);
                    } else if (element.detachEvent) {
                        element.detachEvent('on' + realType, realListener);
                    }
                    lis.splice(len, 1);
                }
            }            
            return element;
        };

        /**
         * ��ȡevent�¼�,�����ͬ�������������
         * @param {Event}
         * @return {Event}
         */
        baidu.getEvent = baidu.event.getEvent = function (event) {
            return window.event || event;
        }

        /**
         * ��ȡevent.target,�����ͬ�������������
         * @param {Event}
         * @return {Target}
         */
        baidu.getTarget = baidu.event.getTarget = function (event) {
            var event = baidu.getEvent(event);
            return event.target || event.srcElement;
        }

        /**
         * ��ֹ�¼���Ĭ����Ϊ
         * @name baidu.event.preventDefault
         * @function
         * @grammar baidu.event.preventDefault(event)
         * @param {Event} event �¼�����
         * @meta standard
         */
        baidu.preventDefault = baidu.event.preventDefault = function (event) {
           var event = baidu.getEvent(event);
           if (event.preventDefault) {
               event.preventDefault();
           } else {
               event.returnValue = false;
           }
        };

        /**
         * ֹͣ�¼�ð�ݴ���
         * @param {Event}
         */
        baidu.stopBubble = baidu.event.stopBubble = function (event) {
            event = baidu.getEvent(event);
            event.stopPropagation ? event.stopPropagation() : event.cancelBubble = true;
        }

    })();

    /** 
     * @exports DrawingManager as BMapLib.DrawingManager 
     */
    var DrawingManager =
        /**
         * DrawingManager��Ĺ��캯��
         * @class �����ƹ����࣬ʵ�������ƹ����<b>���</b>��
         * ʵ��������󣬼��ɵ��ø����ṩ��open
         * ������������ģʽ״̬��
         * Ҳ�ɼ��빤��������ѡ�������
         * 
         * @constructor
         * @param {Map} map Baidu map��ʵ������
         * @param {Json Object} opts ��ѡ������������Ǳ����������ѡ�������<br />
         * {"<b>isOpen</b>" : {Boolean} �Ƿ�������ģʽ
         * <br />"<b>enableDrawingTool</b>" : {Boolean} �Ƿ���ӻ��ƹ������ؼ���Ĭ�ϲ����
         * <br />"<b>drawingToolOptions</b>" : {Json Object} ��ѡ������������Ǳ����������ѡ�����
         * <br />      "<b>anchor</b>" : {ControlAnchor} ͣ��λ�á�Ĭ�����Ͻ�
         * <br />      "<b>offset</b>" : {Size} ƫ��ֵ��
         * <br />      "<b>scale</b>" : {Number} �����������ű���,Ĭ��Ϊ1
         * <br />      "<b>drawingModes</b>" : {DrawingType<Array>} �������Ͽ���ѡ����ֵĻ���ģʽ,����Ҫ��ʾ��DrawingType����������ʽ���룬��[BMAP_DRAWING_MARKER, BMAP_DRAWING_CIRCLE] ��ֻ��ʾ����ͻ�Բ��ѡ��
         * <br />"<b>enableCalculate</b>" : {Boolean} �����Ƿ���в��(����ʱ��)������(��Բ������Ρ�����)
         * <br />"<b>markerOptions</b>" : {CircleOptions} �����ĵ�Ŀ�ѡ�������ο�api�е�<a href="http://developer.baidu.com/map/reference/index.php?title=Class:%E6%80%BB%E7%B1%BB/%E8%A6%86%E7%9B%96%E7%89%A9%E7%B1%BB">��Ӧ��</a>
         * <br />"<b>circleOptions</b>" : {CircleOptions} ������Բ�Ŀ�ѡ�������ο�api�е�<a href="http://developer.baidu.com/map/reference/index.php?title=Class:%E6%80%BB%E7%B1%BB/%E8%A6%86%E7%9B%96%E7%89%A9%E7%B1%BB">��Ӧ��</a>
         * <br />"<b>polylineOptions</b>" : {CircleOptions} �������ߵĿ�ѡ�������ο�api�е�<a href="http://developer.baidu.com/map/reference/index.php?title=Class:%E6%80%BB%E7%B1%BB/%E8%A6%86%E7%9B%96%E7%89%A9%E7%B1%BB">��Ӧ��</a>
         * <br />"<b>polygonOptions</b>" : {PolygonOptions} �����Ķ���εĿ�ѡ�������ο�api�е�<a href="http://developer.baidu.com/map/reference/index.php?title=Class:%E6%80%BB%E7%B1%BB/%E8%A6%86%E7%9B%96%E7%89%A9%E7%B1%BB">��Ӧ��</a>
         * <br />"<b>rectangleOptions</b>" : {PolygonOptions} �����ľ��εĿ�ѡ�������ο�api�е�<a href="http://developer.baidu.com/map/reference/index.php?title=Class:%E6%80%BB%E7%B1%BB/%E8%A6%86%E7%9B%96%E7%89%A9%E7%B1%BB">��Ӧ��</a>
         *
         * @example <b>�ο�ʾ����</b><br />
         * var map = new BMap.Map("container");<br />map.centerAndZoom(new BMap.Point(116.404, 39.915), 15);<br />
         * var myDrawingManagerObject = new BMapLib.DrawingManager(map, {isOpen: true, 
         *     drawingType: BMAP_DRAWING_MARKER, enableDrawingTool: true,
         *     enableCalculate: false,
         *     drawingToolOptions: {
         *         anchor: BMAP_ANCHOR_TOP_LEFT,
         *         offset: new BMap.Size(5, 5),
         *         drawingTypes : [
         *             BMAP_DRAWING_MARKER,
         *             BMAP_DRAWING_CIRCLE,
         *             BMAP_DRAWING_POLYLINE,
         *             BMAP_DRAWING_POLYGON,
         *             BMAP_DRAWING_RECTANGLE 
         *          ]
         *     },
         *     polylineOptions: {
         *         strokeColor: "#333"
         *     });
         */
        BMapLib.DrawingManager = function(map, opts){
            if (!map) {
                return;
            }
            instances.push(this);
            
            opts = opts || {};

            this._initialize(map, opts);
        }

    // ͨ��baidu.lang�µ�inherits��������DrawingManager�̳�baidu.lang.Class
    baidu.lang.inherits(DrawingManager, baidu.lang.Class, "DrawingManager");

    /**
     * ������ͼ�Ļ���ģʽ
     *
     * @example <b>�ο�ʾ����</b><br />
     * myDrawingManagerObject.open();
     */
    DrawingManager.prototype.open = function() {
        // �жϻ���״̬�Ƿ��Ѿ�����
        if (this._isOpen == true){
            return true;
        }
        closeInstanceExcept(this);

        this._open();
    }

    /**
     * �رյ�ͼ�Ļ���״̬
     *
     * @example <b>�ο�ʾ����</b><br />
     * myDrawingManagerObject.close();
     */
    DrawingManager.prototype.close = function() {

        // �жϻ���״̬�Ƿ��Ѿ�����
        if (this._isOpen == false){
            return true;
        }

        this._close();
    }

    /**
     * ���õ�ǰ�Ļ���ģʽ������DrawingType��Ϊ5����ѡ����:
     * <br/>BMAP_DRAWING_MARKER    ����
     * <br/>BMAP_DRAWING_CIRCLE    ��Բ
     * <br/>BMAP_DRAWING_POLYLINE  ����
     * <br/>BMAP_DRAWING_POLYGON   �������
     * <br/>BMAP_DRAWING_RECTANGLE ������
     * @param {DrawingType} DrawingType
     * @return {Boolean} 
     *
     * @example <b>�ο�ʾ����</b><br />
     * myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_POLYLINE);
     */
    DrawingManager.prototype.setDrawingMode = function(drawingType) {
        //�뵱ǰģʽ��һ��ʱ��Ž������°��¼�
        if (this._drawingType != drawingType) {
            closeInstanceExcept(this);
            this._setDrawingMode(drawingType);
        }
    }

    /**
     * ��ȡ��ǰ�Ļ���ģʽ
     * @return {DrawingType} ���Ƶ�ģʽ
     *
     * @example <b>�ο�ʾ����</b><br />
     * alert(myDrawingManagerObject.getDrawingMode());
     */
    DrawingManager.prototype.getDrawingMode = function() {
        return this._drawingType;
    }

    /**
     * �򿪾�����������
     *
     * @example <b>�ο�ʾ����</b><br />
     * myDrawingManagerObject.enableCalculate();
     */
    DrawingManager.prototype.enableCalculate = function() {
        this._enableCalculate = true;
        this._addGeoUtilsLibrary();
    }

    /**
     * �رվ�����������
     *
     * @example <b>�ο�ʾ����</b><br />
     * myDrawingManagerObject.disableCalculate();
     */
    DrawingManager.prototype.disableCalculate = function() {
        this._enableCalculate = false;
    }

	/**
   	 * ��������ɺ��ɷ����¼��Ľӿ�
     * @name DrawingManager#overlaycomplete
     * @event
     * @param {Event Object} e �ص������᷵��event�������������·���ֵ��
     * <br />{"<b>drawingMode</b> : {DrawingType} ��ǰ�Ļ���ģʽ
     * <br />"<b>overlay</b>��{Marker||Polyline||Polygon||Circle} ��Ӧ�Ļ���ģʽ���ض�Ӧ�ĸ�����
     * <br />"<b>calculate</b>��{Number} ��Ҫ��������ģʽ�Ż᷵�����ֵ���������ߵ�ʱ�򷵻ؾ��롢���ƶ���Ρ�Բ������ʱ�򷵻��������λΪ�ף�
     * <br />"<b>label</b>��{Label} �������ʱ�������Map�ϵ�Label����
     *
     * @example <b>�ο�ʾ����</b>
     * myDrawingManagerObject.addEventListener("overlaycomplete", function(e) {
     *     alert(e.drawingMode);
     *     alert(e.overlay);
     *     alert(e.calculate);
     *     alert(e.label);
     * });
     */

    /**
   	 * ���Ƶ���ɺ��ɷ����¼��ӿ�
     * @name DrawingManager#markercomplete
     * @event
     * @param {Marker} overlay �ص������᷵����Ӧ�ĸ����
     * <br />{"<b>overlay</b> : {Marker} 
     *
     * @example <b>�ο�ʾ����</b>
     * myDrawingManagerObject.addEventListener("circlecomplete", function(e, overlay) {
     *     alert(overlay);
     * });
     */

    /**
   	 * ����Բ��ɺ��ɷ����¼��ӿ�
     * @name DrawingManager#circlecomplete
     * @event
     * @param {Circle} overlay �ص������᷵����Ӧ�ĸ����
     * <br />{"<b>overlay</b> : {Circle} 
     */

    /**
   	 * ��������ɺ��ɷ����¼��ӿ�
     * @name DrawingManager#polylinecomplete
     * @event
     * @param {Polyline} overlay �ص������᷵����Ӧ�ĸ����
     * <br />{"<b>overlay</b> : {Polyline} 
     */

    /**
   	 * ���ƶ������ɺ��ɷ����¼��ӿ�
     * @name DrawingManager#polygoncomplete
     * @event
     * @param {Polygon} overlay �ص������᷵����Ӧ�ĸ����
     * <br />{"<b>overlay</b> : {Polygon} 
     */

    /**
   	 * ���ƾ�����ɺ��ɷ����¼��ӿ�
     * @name DrawingManager#rectanglecomplete
     * @event
     * @param {Polygon} overlay �ص������᷵����Ӧ�ĸ����
     * <br />{"<b>overlay</b> : {Polygon} 
     */

    /**
     * ��ʼ��״̬
     * @param {Map} ��ͼʵ��
     * @param {Object} ����
     */
    DrawingManager.prototype._initialize = function(map, opts) {

        /**
         * map����
         * @private
         * @type {Map}
         */
        this._map = map;

        /**
         * ���ö���
         * @private
         * @type {Object}
         */
        this._opts = opts;

        /**
         * ��ǰ�Ļ���ģʽ, Ĭ���ǻ��Ƶ�
         * @private
         * @type {DrawingType}
         */
        this._drawingType = opts.drawingMode || BMAP_DRAWING_MARKER;

        /**
         * �Ƿ������������ƹ��������
         */
        if (opts.enableDrawingTool) {
            var drawingTool  = new DrawingTool(this, opts.drawingToolOptions);
            this._drawingTool = drawingTool;
            map.addControl(drawingTool);
        }

        //�Ƿ������Ƴ������ 
        if (opts.enableCalculate === true) {
            this.enableCalculate();
        } else {
            this.disableCalculate();
        }

        /**
         * �Ƿ��Ѿ������˻���״̬
         * @private
         * @type {Boolean}
         */
        this._isOpen = !!(opts.isOpen === true);
        if (this._isOpen) {
            this._open();
        }

        this.markerOptions    = opts.markerOptions    || {};
        this.circleOptions    = opts.circleOptions    || {};
        this.polylineOptions  = opts.polylineOptions  || {};
        this.polygonOptions   = opts.polygonOptions   || {};
        this.rectangleOptions = opts.rectangleOptions || {};

    },

    /**
     * ������ͼ�Ļ���״̬
     * @return {Boolean}����������״̬�ɹ�������true�����򷵻�false��
     */
    DrawingManager.prototype._open = function() {

        this._isOpen = true;

        //������֣���������������������������
        if (!this._mask) {
            this._mask = new Mask();
        }
        this._map.addOverlay(this._mask);
        this._setDrawingMode(this._drawingType);

    }

    /**
     * ���õ�ǰ�Ļ���ģʽ
     * @param {DrawingType}
     */
    DrawingManager.prototype._setDrawingMode = function(drawingType) {

        this._drawingType = drawingType;

        /**
         * �����༭״̬ʱ������½����¼���
         */
        if (this._isOpen) {
            //���֮ǰ���Զ����¼�
            this._mask.__listeners = {};

            switch (drawingType) {
                case BMAP_DRAWING_MARKER:
                    this._bindMarker();
                    break;
                case BMAP_DRAWING_CIRCLE:
                    this._bindCircle();
                    break;
                case BMAP_DRAWING_POLYLINE:
                case BMAP_DRAWING_POLYGON:
                    this._bindPolylineOrPolygon();
                    break;
                case BMAP_DRAWING_RECTANGLE:
                    this._bindRectangle();
                    break;
            }
        }

        /** 
         * �������˹���������Ҳ��Ҫ�ı乤��������ʽ
         */
        if (this._drawingTool && this._isOpen) {
            this._drawingTool.setStyleByDrawingMode(drawingType);
        }
    }

    /**
     * �رյ�ͼ�Ļ���״̬
     * @return {Boolean}���رջ���״̬�ɹ�������true�����򷵻�false��
     */
    DrawingManager.prototype._close = function() {

        this._isOpen = false;

        if (this._mask) {
            this._map.removeOverlay(this._mask);
        }

        /** 
         * �������˹���������ر�ʱ�򽫹�������ʽ����Ϊ��ק��ͼ
         */
        if (this._drawingTool) {
            this._drawingTool.setStyleByDrawingMode("hander");
        }
    }

    /**
     * ����껭����¼�
     */
    DrawingManager.prototype._bindMarker = function() {

        var me   = this,
            map  = this._map,
            mask = this._mask;

        /**
         * ��������¼�
         */
        var clickAction = function (e) {
            // ����ͼ�����marker
            var marker = new BMap.Marker(e.point, me.markerOptions);
            map.addOverlay(marker);
            me._dispatchOverlayComplete(marker);
        }

        mask.addEventListener('click', clickAction);
    }

    /**
     * ����껭Բ���¼�
     */
    DrawingManager.prototype._bindCircle = function() {

        var me           = this,
            map          = this._map,
            mask         = this._mask,
            circle       = null,
            centerPoint  = null; //Բ�����ĵ�

        /**
         * ��ʼ����Բ��
         */
        var startAction = function (e) {
            centerPoint = e.point;
            circle = new BMap.Circle(centerPoint, 0, me.circleOptions);
            map.addOverlay(circle);
            mask.enableEdgeMove();
            mask.addEventListener('mousemove', moveAction);
            baidu.on(document, 'mouseup', endAction);
        }

        /**
         * ����Բ�ι����У�����ƶ����̵��¼�
         */
        var moveAction = function(e) {
            circle.setRadius(me._map.getDistance(centerPoint, e.point));
        }

        /**
         * ����Բ�ν���
         */
        var endAction = function (e) {
            var calculate = me._calculate(circle, e.point);
            me._dispatchOverlayComplete(circle, calculate);
            centerPoint = null;
            mask.disableEdgeMove();
            mask.removeEventListener('mousemove', moveAction);
            baidu.un(document, 'mouseup', endAction);
        }

        /**
         * �������ʼ��
         */
        var mousedownAction = function (e) {
            baidu.preventDefault(e);
            baidu.stopBubble(e);
            if (centerPoint == null) {
                startAction(e);
            } 
        }

        mask.addEventListener('mousedown', mousedownAction);
    }

    /**
     * ���ߺͻ�����������ԱȽϴ󣬹���һ������
     */
    DrawingManager.prototype._bindPolylineOrPolygon = function() {

        var me           = this,
            map          = this._map,
            mask         = this._mask,
            points       = [],   //�û����Ƶĵ�
            drawPoint    = null; //ʵ����Ҫ���ڵ�ͼ�ϵĵ�
            overlay      = null,
            isBinded     = false;
 
         /**
          * ��������¼�
          */
         var startAction = function (e) {
             points.push(e.point);
             drawPoint = points.concat(points[points.length - 1]);
             if (points.length == 1) {
                 if (me._drawingType == BMAP_DRAWING_POLYLINE) {
                     overlay = new BMap.Polyline(drawPoint, me.polylineOptions);
                 } else if (me._drawingType == BMAP_DRAWING_POLYGON) {
                     overlay = new BMap.Polygon(drawPoint, me.polygonOptions);
                 }
                 map.addOverlay(overlay);
             } else {
                 overlay.setPath(drawPoint);
             }
			/**lxc**/ overlay.enableEditing();
             if (!isBinded) {
                 isBinded = true;
                 mask.enableEdgeMove();
                 mask.addEventListener('mousemove', mousemoveAction);
                 mask.addEventListener('dblclick', dblclickAction);
             }
			 console.log(e.point);

			 //document.getElementById("drawPointes").innerHTML+=e.clientX+","+e.clientY+"; "; 
			/**lxc**/ document.getElementById("drawPointes").innerHTML+=e.point.lat+","+e.point.lng+"; "; 
         }
 
         /**
          * ����ƶ����̵��¼�
          */
         var mousemoveAction = function(e) {
             overlay.setPositionAt(drawPoint.length - 1, e.point);
			 /**lxc**/
			  document.getElementById("drawPointX").innerHTML=e.clientX; 
			   document.getElementById("drawPointY").innerHTML=e.clientY; 
         }
 
         /**
          * ���˫�����¼�
          */
         var dblclickAction = function (e) {
             baidu.stopBubble(e);
             isBinded = false;
             mask.disableEdgeMove();
             mask.removeEventListener('mousemove', mousemoveAction);
             mask.removeEventListener('dblclick', dblclickAction);
             overlay.setPath(points);
             var calculate = me._calculate(overlay, points.pop());
             me._dispatchOverlayComplete(overlay, calculate);
             points.length = 0;
             drawPoint.length = 0;
         }
 
         mask.addEventListener('click', startAction);
 
         //˫��ʱ�򲻷Ŵ��ͼ����
         mask.addEventListener('dblclick', function(e){
             baidu.stopBubble(e);
         });
     }
 
     /**
      * ����껭���ε��¼�
      */
     DrawingManager.prototype._bindRectangle = function() {
 
         var me           = this,
             map          = this._map,
             mask         = this._mask,
             polygon      = null,
             startPoint   = null;
 
         /**
          * ��ʼ���ƾ���
          */
         var startAction = function (e) {
             baidu.stopBubble(e);
             baidu.preventDefault(e);
             startPoint = e.point;
             var endPoint = startPoint;
             polygon = new BMap.Polygon(me._getRectanglePoint(startPoint, endPoint), me.rectangleOptions);
             map.addOverlay(polygon);
             mask.enableEdgeMove();
             mask.addEventListener('mousemove', moveAction);
             baidu.on(document, 'mouseup', endAction);
         }
 
         /**
          * ���ƾ��ι����У�����ƶ����̵��¼�
          */
         var moveAction = function(e) {
             polygon.setPath(me._getRectanglePoint(startPoint, e.point));
         }
 
         /**
          * ���ƾ��ν���
          */
         var endAction = function (e) {
             var calculate = me._calculate(polygon, polygon.getPath()[2]);
             me._dispatchOverlayComplete(polygon, calculate);
             startPoint = null;
             mask.disableEdgeMove();
             mask.removeEventListener('mousemove', moveAction);
             baidu.un(document, 'mouseup', endAction);
         }
 
         mask.addEventListener('mousedown', startAction);
     }
 
     /**
      * �����ʾ������ͼ�ε�������߳���
      * @param {overlay} ������
      * @param {point} ��ʾ��λ��
      */
     DrawingManager.prototype._calculate = function (overlay, point) {
         var result = {
             data  : 0,    //��������ĳ��Ȼ����
             label : null  //��ʾ���Ȼ������label����
         };
         if (this._enableCalculate && BMapLib.GeoUtils) {
             var type = overlay.toString();
             //��ͬ��������ò�ͬ�ļ��㷽��
             switch (type) {
                 case "[object Polyline]":
                     result.data = BMapLib.GeoUtils.getPolylineDistance(overlay);
                     break;
                 case "[object Polygon]":
                     result.data = BMapLib.GeoUtils.getPolygonArea(overlay);
                     break;
                 case "[object Circle]":
                     var radius = overlay.getRadius();
                     result.data = Math.PI * radius * radius;
                     break;
             }
             //һ���������
             if (!result.data || result.data < 0) {
                 result.data = 0;
             } else {
                 //����2λС��λ
                 result.data = result.data.toFixed(2);
             }
             result.label = this._addLabel(point, result.data);
         }
         return result;
     }
 
     /**
      * �������Ͳ��湦����Ҫ������GeoUtils��
      * ���������ж��û��Ƿ��Ѿ�����,��δ��������js��̬����
      */
     DrawingManager.prototype._addGeoUtilsLibrary = function () {
         if (!BMapLib.GeoUtils) {
             var script = document.createElement('script');
             script.setAttribute("type", "text/javascript");
             script.setAttribute("src", 'http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js');
             document.body.appendChild(script);
         }
     }
 
     /**
      * ���ͼ������ı���ע
      * @param {Point}
      * @param {String} ������ʾ������
      */
     DrawingManager.prototype._addLabel = function (point, content) {
         var label = new BMap.Label(content, {
             position: point
         });
         this._map.addOverlay(label);
         return label;
     }
 
     /**
      * �������յ��ȡ���ε��ĸ�����
      * @param {Point} ���
      * @param {Point} �յ�
      */
     DrawingManager.prototype._getRectanglePoint = function (startPoint, endPoint) {
         return [
             new BMap.Point(startPoint.lng,startPoint.lat),
             new BMap.Point(endPoint.lng,startPoint.lat),
             new BMap.Point(endPoint.lng,endPoint.lat),
             new BMap.Point(startPoint.lng,endPoint.lat)
         ];
     }
 
     /**
      * �ɷ��¼�
      */
     DrawingManager.prototype._dispatchOverlayComplete = function (overlay, calculate) {
		 var options;
		 if(calculate==null){
			 options = {
				 'overlay'     : overlay,
				 'drawingMode' : this._drawingType,
				 'calculate'   : null,
				 'label'       : null
			 };
		 }else{
			 
			  options = {
				 'overlay'     : overlay,
				 'drawingMode' : this._drawingType,
				 'calculate'   : calculate.data  || null,
				 'label'       : calculate.label || null
			 };
		 }
         this.dispatchEvent(this._drawingType + 'complete', overlay);
         this.dispatchEvent('overlaycomplete', options);
     }
 
     /**
      * �������ֶ���
      */
     function Mask(){
         /**
          * ��굽��ͼ��Ե��ʱ���Ƿ��Զ�ƽ�Ƶ�ͼ
          */
         this._enableEdgeMove = false;
     }
 
     Mask.prototype = new BMap.Overlay();
 
     /**
      * ���ﲻʹ��api�е��Զ����¼�����Ϊ�˸����ʹ��
      */
     Mask.prototype.dispatchEvent = baidu.lang.Class.prototype.dispatchEvent;
     Mask.prototype.addEventListener = baidu.lang.Class.prototype.addEventListener;
     Mask.prototype.removeEventListener = baidu.lang.Class.prototype.removeEventListener;
 
     Mask.prototype.initialize = function(map){
         var me = this;
         this._map = map;
         var div = this.container = document.createElement("div");
         var size = this._map.getSize();
         div.style.cssText = "position:absolute;background:url(about:blank);cursor:crosshair;width:" + size.width + "px;height:" + size.height + "px";
         this._map.addEventListener('resize', function(e) {
             me._adjustSize(e.size);
         });
         this._map.getPanes().floatPane.appendChild(div);
         this._bind();
         return div; 
     };
 
     Mask.prototype.draw = function() {
         var map   = this._map,
             point = map.pixelToPoint(new BMap.Pixel(0, 0)),
             pixel = map.pointToOverlayPixel(point);
         this.container.style.left = pixel.x + "px";
         this.container.style.top  = pixel.y + "px"; 
     };
 
     /**
      * ������굽��ͼ��Ե���Զ�ƽ�Ƶ�ͼ
      */
     Mask.prototype.enableEdgeMove = function() {
         this._enableEdgeMove = true;
     }
 
     /**
      * �ر���굽��ͼ��Ե���Զ�ƽ�Ƶ�ͼ
      */
     Mask.prototype.disableEdgeMove = function() {
         clearInterval(this._edgeMoveTimer);
         this._enableEdgeMove = false;
     }
 
     /**
      * ���¼�,�ɷ��Զ����¼�
      */
     Mask.prototype._bind = function() {
 
         var me = this,
             map = this._map,
             container = this.container,
             lastMousedownXY = null,
             lastClickXY = null;
 
         /**
          * ����event�����ȡ����xy�������
          * @param {Event}
          * @return {Object} {x:e.x, y:e.y}
          */
         var getXYbyEvent = function(e){
             return {
                 x : e.clientX,
                 y : e.clientY
             }
         };
 
         var domEvent = function(e) {
             var type = e.type;
                 e = baidu.getEvent(e);
                 point = me.getDrawPoint(e); //��ǰ������ڵ�ĵ�������
 
             var dispatchEvent = function(type) {
                 e.point = point;
                 me.dispatchEvent(e);
             }
 
             if (type == "mousedown") {
                 lastMousedownXY = getXYbyEvent(e);
             }
 
             var nowXY = getXYbyEvent(e);
             //click����һЩ���⴦���ɷ�������ͬ�¼���������dom�¼��ɷ�
             if (type == "click") {
                 //��������̲������ƶ����ɷ�click��dblclick
                 if (Math.abs(nowXY.x - lastMousedownXY.x) < 5 && Math.abs(nowXY.y - lastMousedownXY.y) < 5 ) {
                     if (!lastClickXY || !(Math.abs(nowXY.x - lastClickXY.x) < 5 && Math.abs(nowXY.y - lastClickXY.y) < 5)) {
                         dispatchEvent('click');
                         lastClickXY = getXYbyEvent(e);
                     } else {
                         lastClickXY = null;
                     }
                 }
             } else {
                 dispatchEvent(type);
             }
         }
 
         /**
          * ���¼������ֲ���¼����󶨵�domEvent������
          */
         var events = ['click', 'mousedown', 'mousemove', 'mouseup', 'dblclick'],
             index = events.length;
         while (index--) {
             baidu.on(container, events[index], domEvent);
         }
 
         //����ƶ������У�����ͼ��Ե���Զ�ƽ�Ƶ�ͼ
         baidu.on(container, 'mousemove', function(e) {
             if (me._enableEdgeMove) {
                 me.mousemoveAction(e);
             }
         });
     };
 
     //����ƶ������У�����ͼ��Ե���Զ�ƽ�Ƶ�ͼ
     Mask.prototype.mousemoveAction = function(e) {
         function getClientPosition(e) {
             var clientX = e.clientX,
                 clientY = e.clientY;
             if (e.changedTouches) {
                 clientX = e.changedTouches[0].clientX;
                 clientY = e.changedTouches[0].clientY;
             }
             return new BMap.Pixel(clientX, clientY);
         }
 
         var map       = this._map,
             me        = this,
             pixel     = map.pointToPixel(this.getDrawPoint(e)),
             clientPos = getClientPosition(e),
             offsetX   = clientPos.x - pixel.x,
             offsetY   = clientPos.y - pixel.y;
         pixel = new BMap.Pixel((clientPos.x - offsetX), (clientPos.y - offsetY));
         this._draggingMovePixel = pixel;
         var point = map.pixelToPoint(pixel),
             eventObj = {
                 pixel: pixel,
                 point: point
             };
         // ��ק����ͼ��Ե�ƶ���ͼ
         this._panByX = this._panByY = 0;
         if (pixel.x <= 20 || pixel.x >= map.width - 20
             || pixel.y <= 50 || pixel.y >= map.height - 10) {
             if (pixel.x <= 20) {
                 this._panByX = 8;
             } else if (pixel.x >= map.width - 20) {
                 this._panByX = -8;
             }
             if (pixel.y <= 50) {
                 this._panByY = 8;
             } else if (pixel.y >= map.height - 10) {
                 this._panByY = -8;
             }
             if (!this._edgeMoveTimer) {
                 this._edgeMoveTimer = setInterval(function(){
                     map.panBy(me._panByX, me._panByY, {"noAnimation": true});
                 }, 30);
             }
         } else {
             if (this._edgeMoveTimer) {
                 clearInterval(this._edgeMoveTimer);
                 this._edgeMoveTimer = null;
             }
         }
     }
 
     /*
      * ������С
      * @param {Size}
      */
     Mask.prototype._adjustSize = function(size) {
         this.container.style.width  = size.width + 'px';
         this.container.style.height = size.height + 'px';
     };
 
     /**
      * ��ȡ��ǰ���Ƶ�ĵ�������
      *
      * @param {Event} e e����
      * @return Point�����λ����Ϣ
      */
     Mask.prototype.getDrawPoint = function(e) {
         
         var map = this._map,
         trigger = baidu.getTarget(e),
         x = e.offsetX || e.layerX || 0,
         y = e.offsetY || e.layerY || 0;
         if (trigger.nodeType != 1) trigger = trigger.parentNode;
         while (trigger && trigger != map.getContainer()) {
             if (!(trigger.clientWidth == 0 &&
                 trigger.clientHeight == 0 &&
                 trigger.offsetParent && trigger.offsetParent.nodeName == 'TD')) {
                 x += trigger.offsetLeft || 0;
                 y += trigger.offsetTop || 0;
             }
             trigger = trigger.offsetParent;
         }
         var pixel = new BMap.Pixel(x, y);
         var point = map.pixelToPoint(pixel);
         return point;
 
     }
 
     /**
      * ���ƹ�����壬�Զ���ؼ�
      */
     function DrawingTool(drawingManager, drawingToolOptions) {
         this.drawingManager = drawingManager;
 
         drawingToolOptions = this.drawingToolOptions = drawingToolOptions || {};
         // Ĭ��ͣ��λ�ú�ƫ����
         this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
         this.defaultOffset = new BMap.Size(10, 10);
 
         //Ĭ�����й���������ʾ
         this.defaultDrawingModes = [
             BMAP_DRAWING_MARKER,
             BMAP_DRAWING_CIRCLE,
             BMAP_DRAWING_POLYLINE,
             BMAP_DRAWING_POLYGON,
             BMAP_DRAWING_RECTANGLE
         ];
         //����������ʾ�Ļ���ģʽ
         if (drawingToolOptions.drawingModes) {
             this.drawingModes = drawingToolOptions.drawingModes;
         } else {
             this.drawingModes = this.defaultDrawingModes
         }
 
         //�û�����ͣ��λ�ú�ƫ����
         if (drawingToolOptions.anchor) {
             this.setAnchor(drawingToolOptions.anchor);
         }
         if (drawingToolOptions.offset) {
             this.setOffset(drawingToolOptions.offset);
         }
     }
 
     // ͨ��JavaScript��prototype���Լ̳���BMap.Control
     DrawingTool.prototype = new BMap.Control();
 
     // �Զ���ؼ�����ʵ���Լ���initialize����,���ҽ��ؼ���DOMԪ�ط���
     // �ڱ������д�����divԪ����Ϊ�ؼ�������,��������ӵ���ͼ������
     DrawingTool.prototype.initialize = function(map){
         // ����һ��DOMԪ��
         var container = this.container = document.createElement("div");
         container.className = "BMapLib_Drawing";
         //�����������߿���Ӱ
         var panel = this.panel = document.createElement("div");
         panel.className = "BMapLib_Drawing_panel";
         if (this.drawingToolOptions && this.drawingToolOptions.scale) {
             this._setScale(this.drawingToolOptions.scale);
         }
         container.appendChild(panel);
         // �������
         panel.innerHTML = this._generalHtml();
         //���¼�
         this._bind(panel);
         // ���DOMԪ�ص���ͼ��
         map.getContainer().appendChild(container);
         // ��DOMԪ�ط���
         return container;
     }
 
     //���ɹ�������htmlԪ��
     DrawingTool.prototype._generalHtml = function(map){
 
         //��꾭���������ϵ���ʾ��Ϣ
         var tips = {};
         tips["hander"]               = "�϶���ͼ";
         tips[BMAP_DRAWING_MARKER]    = "����";
         tips[BMAP_DRAWING_CIRCLE]    = "��Բ";
         tips[BMAP_DRAWING_POLYLINE]  = "������";
         tips[BMAP_DRAWING_POLYGON]   = "�������";
         tips[BMAP_DRAWING_RECTANGLE] = "������";
 
         var getItem = function(className, drawingType) {
             return '<a class="' + className + '" drawingType="' + drawingType + '" href="javascript:void(0)" title="' + tips[drawingType] + '" onfocus="this.blur()"></a>';
         }
 
         var html = [];
         html.push(getItem("BMapLib_box BMapLib_hander", "hander"));
         for (var i = 0, len = this.drawingModes.length; i < len; i++) {
             var classStr = 'BMapLib_box BMapLib_' + this.drawingModes[i];
             if (i == len-1) {
                 classStr += ' BMapLib_last';
             }
             html.push(getItem(classStr, this.drawingModes[i]));
         }
         return html.join('');
     }
 
     /**
      * ���ù����������ű���
      */
     DrawingTool.prototype._setScale = function(scale){
         var width  = 390,
             height = 50,
             ml = -parseInt((width - width * scale) / 2, 10),
             mt = -parseInt((height - height * scale) / 2, 10);
         this.container.style.cssText = [
             "-moz-transform: scale(" + scale + ");",
             "-o-transform: scale(" + scale + ");",
             "-webkit-transform: scale(" + scale + ");",
             "transform: scale(" + scale + ");",
             "margin-left:" + ml + "px;",
             "margin-top:" + mt + "px;",
             "*margin-left:0px;", //ie6��7
             "*margin-top:0px;",  //ie6��7
             "margin-left:0px\\0;", //ie8
             "margin-top:0px\\0;",  //ie8
             //ie��ʹ���˾�
             "filter: progid:DXImageTransform.Microsoft.Matrix(",
             "M11=" + scale + ",",
             "M12=0,",
             "M21=0,",
             "M22=" + scale + ",",
             "SizingMethod='auto expand');"
         ].join('');
     }
 
     //�󶨹��������¼�
     DrawingTool.prototype._bind = function(panel){
         var me = this;
         baidu.on(this.panel, 'click', function (e) {
             var target = baidu.getTarget(e);
             var drawingType = target.getAttribute('drawingType');
             me.setStyleByDrawingMode(drawingType);
             me._bindEventByDraingMode(drawingType);
         });
     }
 
     //���ù�������ǰѡ�е�����ʽ
     DrawingTool.prototype.setStyleByDrawingMode = function(drawingType){
         if (!drawingType) {
             return;
         }
         var boxs = this.panel.getElementsByTagName("a");
         for (var i = 0, len = boxs.length; i < len; i++) {
             var box = boxs[i];
             if (box.getAttribute('drawingType') == drawingType) {
                 var classStr = "BMapLib_box BMapLib_" + drawingType + "_hover";
                 if (i == len - 1) {
                     classStr += " BMapLib_last";
                 }
                 box.className = classStr;
             } else {
                 box.className = box.className.replace(/_hover/, "");
             }
         }
     }
 
     //���ù�������ǰѡ�е�����ʽ
     DrawingTool.prototype._bindEventByDraingMode = function(drawingType){
         var drawingManager = this.drawingManager;
         //������ק��ͼ�İ�ť��
         if (drawingType == "hander") {
             drawingManager.close();
         } else {
             drawingManager.setDrawingMode(drawingType);
             drawingManager.open();
         }
     }
 
     //�����洢�û�ʵ����������drawingmanager����
     var instances = [];
 
     /*
      * �ر�����ʵ���Ļ���ģʽ
      * @param {DrawingManager} ��ǰ��ʵ��
      */
     function closeInstanceExcept(instance) {
         var index = instances.length;
         while (index--) {
             if (instances[index] != instance) {
                 instances[index].close();
             }
         }
     }
 
 })();
 