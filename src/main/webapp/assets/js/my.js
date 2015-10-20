var modalOptions = {
	url 	: '',
	fade	: 'fade',
	close	: true,
	title	: 'title',
	btn		: false,
	okbtn	: '确定',
	qubtn	: '取消',
	msg		: 'msg',
	big		: false,
	show	: false,
	remote	: false,
	backdrop: 'static',
	keyboard: true,
	style	: ''
};
function getModalStr(opt){
	var start = '<div class="modal '+opt.fade+'" id="bsmodal" tabindex="-1" role="dialog" aria-labelledby="bsmodaltitle" aria-hidden="true" style="position:fixed;top:20px;'+opt.style+'">';
	if(opt.big){
		start += '<div class="modal-dialog modal-lg"><div class="modal-content">';
	}else{
		start += '<div class="modal-dialog"><div class="modal-content">';
	}
	var end = '</div></div></div>';
	
	var head = '<div class="modal-header">';
	if(opt.close){
		head += '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
	}
	head += '<h3 class="modal-title" id="bsmodaltitle">'+opt.title+'</h3></div>';
	var body = '<div class="modal-body"><p><h4>'+opt.msg+'</h4></p></div>';
	var foot = '<div class="modal-footer"><button type="button" class="btn btn-primary bsok">'+opt.okbtn+'</button>';
	if(opt.btn){
		foot += '<button type="button" class="btn btn-default bscancel">'+opt.qubtn+'</button>';
	}
	foot += '</div>';
	
	return start + head + body + foot + end;
};

function rebind(obj, event, func){
	$(document).off(event, obj).on(event, obj, func);
};

function dialog(options, func){
	// options
	var opt = $.extend({}, modalOptions, options);
	opt.big = true;
	
	// append
	$('body').append(getModalStr(opt));
	
	// ajax page
	var html = ajax({url:options.url, dataType:'html'});
	$('#bsmodal div.modal-body').empty().append(html);
	
	// init
	var $modal = $('#bsmodal'); 
	$modal.modal(opt);
	
	// bind
	rebind('button.bsok', 'click', function(){
		var flag = true;
		if(func){
			flag = func();
		}
		
		if(flag){
			$modal.modal('hide');
		}
	});
	rebind('#bsmodal', 'hidden.bs.modal', function(){
		$modal.remove();
	});
	
	// show
	$modal.modal('show');
};


//////////////////////////////////////////////////////
var ajaxOptions = {
	url 	: '',
	data 	: {},
	type 	: 'post',
	dataType: 'json',
	async 	: false
};

function ajax(options){
	if(!options){
		alert('need options');
	}
	var opt = $.extend({}, ajaxOptions);
	if(typeof options == 'string'){
		opt.url = options;
	}else{
		$.extend(opt, options);
	}
	opt.url = baseUrl + opt.url;
	$.ajax(opt).done(function(obj){res = obj;});
	return res;
}

/////////////////////////////////////////////////////////////////
var msgoptions = {
	msg  : 'msg',
	type : 'info',
	time : 2000
};
function msgstr(msg, type){
	return '<div class="alert alert-'+type+' alert-dismissible" role="alert" style="display:none;position:fixed;top:0;left:0;width:100%;z-index:2001;margin:0;text-align:center;" id="bsalert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+msg+'</div>';
};
	
function msg(options){
	var opt = $.extend({},msgoptions);
	
	if(typeof options == 'string'){
		opt.msg = options;
	}else{
		$.extend(opt, options);
	}
	
	$('body').prepend(msgstr(opt.msg,opt.type));
	$('#bsalert').slideDown();
	setTimeout(function(){
		$('#bsalert').slideUp(function(){
			$('#bsalert').remove();
		});
	},opt.time);
};

////////////////////////
//翻页
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#fenyeForm input,select").each(function(index, domEle){
		if (domEle.placeholder == domEle.value) {
			domEle.disabled = true;
		}
	});
	$("#fenyeForm").submit();
	return false;
 }