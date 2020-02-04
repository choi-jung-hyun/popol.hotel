<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ȸ������</title>
<!-- Bootstrap -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- font awesome -->
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<!-- Custom style -->
<link rel="stylesheet" href="/resources/css/style.css" media="screen" title="no title" charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js//api/daumAddr.js"></script>
<script>
$(document).ready(function () {
	   $(function () {
	            //�޴�����ȣ �Է½� �ڵ� ������
	            $('#userPhone').keydown(function (event) {
	            	let lastNum;
	            	var key = event.charCode || event.keyCode || 0;
	             $text = $(this); 
	             if (key !== 8 && key !== 9) {
	                 if ($text.val().length === 3) {
	                     $text.val($text.val() + '-');
	                 }
	                 lastNum =  $text.val().substr(2,1);//�޴��� ���ڸ� ��������ȣ�� �ɷ���
	                 console.log(lastNum);
	              	if(lastNum == "0"){
	              		if ($text.val().length === 8) {
	                    	$text.val($text.val() + '-');
	                 	}
	              		
	              	}else{
	              		if ($text.val().length === 7) {
	                    	$text.val($text.val() + '-');
	                 	}
	                 	
	              	}
	             }

	             return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
				 // Key 8�� �齺���̽�, Key 9�� ��, Key 46�� Delete ���� 0 ~ 9����, Key 96 ~ 105���� �ѹ���Ʈ
				 // �Ѹ���� JQuery 0 ~~~ 9 ���� �齺���̽�, ��, Delete Ű �ѹ��е�ܿ��� �Է¸���
	         })
	   });

	});
	//��ҹ�ư Ŭ���̺�Ʈ
	function back() {
		location.href = '/login/loginView.do';
	}
	
	//���̵� �ߺ� Ȯ��
	function email_check(){
		
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if($("#userEmail").val() == '' || $("#userEmail").val() == null){
			alert("�̸����� �Է����ּ���.");
			return false;
		}
		
		if(!regExp.test($("#userEmail").val())){//������ �ٸ����
			alert("�̸��� ������ �����ʽ��ϴ�.");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url  : "/member/email_check.do",
			data : {
				userEmail : $("#userEmail").val()
			}
		
		}).done(function(data){
			
			if(data.resultCode > 0){
				$("#emailCheck_yn").val("N");
				$("#userEmail").focus();
				alert(data.Msg);//�ߺ��Ⱦ��̵�
			}else{
				alert(data.Msg);//��밡��
				$("#emailCheck_yn").val("Y");
				
				//��밡���� ��� �������ϰ� ��ư ���º���
				$("#email_chk").hide();
				$("#userEmail").prop("readOnly","true");
				$("#email_modify").show();
			}
		})
		
	}
	//�̸��� �ߺ� ��ư �̺�Ʈ
	function email_modify(){
		
		$("#emailCheck_yn").val("N"); //�ٽ� �ߺ�Ȯ���ؾ���
		$("#userEmail").focus();
		$("#userEmail").removeAttr("readOnly");
		$("#email_modify").hide();
		$("#email_chk").show();
	}
	
	//Ư������üũ 
	function special_replace(text){
		
		var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
		 
		if(regExp.test(text)){//Ư������ �ִ°�� false
			return false;
		}
	}
	//�̸��� ����üũ
	function emailChk(email){
		
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		if(!regExp.test(email)){//������ �ٸ����
			return false;
		}
		
	}
	//��й�ȣ ���Խ� 
	function pwChk(pass){
		
		var regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/;

		if(pass == '' || pass == null){
			alert("�н����带 �Է����ּ���!");
			return false;
		}
		
		if(!regExp.test(pass)){// ��й�ȣ ���Ŀ� �����ʴ°��
			alert("��й�ȣ�� 8~12�ڸ� Ư������, ����, ������ �����Ͽ� �ۼ��ؾߵ˴ϴ�.");
			$("#userPw").val("");
			$("#userPwChk").val("");
			return false;
		}
		pwMatch(pass);//���Խ�������ߴٸ� ������ �Է¹޾Ѵ� �н������ ��ġüũ����.
	}
	//��й�ȣ ��ġ üũ
	function pwMatch(pw){
		
		if(pw != $("#userPwChk").val()){
			alert("�н����尡 ��ġ���� �ʽ��ϴ�.");
			$("#userPwChk").val("");
			return false;
		}
	}
	function signSub(){
		//�̸� ����üũ
		if($("#userNm").val() == '' || $("#userNm").val() == null){
			alert("�̸��� ������ �Է��� �� �����ϴ�.");
			return false;
		}
		//�̸��� Ư������ üũ
		if(special_replace($("#userNm").val()) == false){
			alert("�̸��� Ư�����ڸ� ����� �� �����ϴ�.");
			$("#userNm").val("");
			return false;
		}
		//�̸��� ����üũ
		if(emailChk($("#userEmail").val()) == false){
			alert("�̸��� ������ �������� �ʽ��ϴ�.");
			$("#userEmail").val("");
			return false;
		}
		//��й�ȣ ���Խ� �� ��й�ȣ ��ġ üũ
		if(pwChk($("#userPw").val()) == false){
			return false;
		}
		//�޴�����ȣ ����üũ
		if(userPhone == '' || userPhone == null){
			alert("�޴��� ��ȣ�� ������ �Է��� �� �����ϴ�.");
			return false;
		}
		//�ּ�üũ
		if($("#post_code").val() == '' || $("#post_code").val() == null){
			alert("�����ȣ�� �Է����ּ���.");
			return false;
		} 
		if($("#addr").val() == '' || $("#addr").val() == null){
			alert("�ּҸ� �Է����ּ���.");
			return false;
		}
		if($("#detail_addr").val() == '' || $("#detail_addr").val() == null){
			alert("���ּҸ� �Է����ּ���.");
			return false;
		}
		if(special_replace($("#detail_addr").val()) == false){
			alert("���ּҿ� Ư�����ڸ� �Է��� �� �����ϴ�.");
			return false;
		}
		//��� üũ
		if($("#agree").is(":checked") == false){
			alert("������Ǹ� üũ���ּ���.");
			return false;
		}
		
		if($("#emailCheck_yn").val() != "Y"){
			alert("�̸��� �ߺ� üũ�� Ȯ�����ּ���.");
			return false;
		}
		
		$.ajax({
			type : 'POST',
			url  : '/member/sign_upAct.do',
			data : {
				userNm		: $("#userNm").val(),
				userEmail 	: $("#userEmail").val(),
				userPass 	: $("#userPw").val(),
				userPhone 	: $("#userPhone").val(),
				post_code 	: $("#post_code").val(),
				addr 		: $("#addr").val(),
				detail_addr : $("#detail_addr").val()
			},
			
		}).done(function(data){
			if(data.resultCode > 0){//����
				alert(data.Msg);
				location.href = "/login/loginView.do";
			}else{					//����
				alert(data.Msg);
			}
		})
	}
</script>
</head>
<body>
	<article class="container">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3">
				<h3>ȸ������</h3>
			</div>
		</div>
		<div class="col-sm-6 col-md-offset-3">
			<form role="form">
			<input type="hidden" id="emailCheck_yn" value="N">
				<div class="form-group">
					���� <input type="text" class="form-control" id="userNm" placeholder="�̸��� �Է��� �ּ���." maxlength="6">
				</div>
				<div class="form-group">
					�̸��� �ּ� <input type="email" class="form-control" id="userEmail" placeholder="�̸��� �ּҸ� �Է����ּ���." maxlength="30">
							<a href="javascript:email_check();" id="email_chk" class="btn btn-primary"> �ߺ�Ȯ�� <i class="fa fa-check spaceLeft"></i></a>
							<a href="javascript:email_modify();" id="email_modify" class="btn btn-primary" style="display:none;"> ���� <i class="fa fa-check spaceLeft"></i></a>
				</div>
				<div class="form-group">
					��й�ȣ <input type="password" class="form-control" id="userPw" placeholder="��й�ȣ�� �Է����ּ���." maxlength="12">
				</div>
				<div class="form-group">
					��й�ȣ Ȯ��<input type="password" class="form-control" id="userPwChk" placeholder="��й�ȣ Ȯ���� ���� �ٽ��ѹ� �Է� �� �ּ���." maxlength="12">
				</div>
				<div class="form-group">
					�޴��� ��ȣ <input type="text" class="form-control" id="userPhone" placeholder="�޴�����ȣ�� �Է��� �ּ���."  maxlength="13">
				</div>
				
				�ּ�<br>
				<div class="form-group">
				<input type="text"  id="post_code" placeholder="�����ȣ" readOnly="readOnly">
				<input type="button" onclick="sample6_execDaumPostcode()" value="�����ȣ ã��">
				</div>
				<div class="form-group" style="margin-top:5px;">
					<input type="text" class="form-control" id="addr" placeholder="�ּ�" readOnly="readOnly">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="detail_addr" placeholder="���ּ�">
				</div>

				<div class="form-group">
					<label>��� ����</label>
					<div data-toggle="buttons">
						<label class="btn btn-primary active"> <span
							class="fa fa-check"></span> <input id="agree" type="checkbox"
							autocomplete="off" checked>
						</label> <a href="#">�̿���</a>�� �����մϴ�.
					</div>
				</div>

				<div class="form-group text-center">
					<a href="javascript:signSub();" class="btn btn-primary"> ȸ������<i
						class="fa fa-check spaceLeft"></i>
					</a> <a href="javascript:back();" class="btn btn-warning"> �������<i
						class="fa fa-times spaceLeft"></i>
					</a>
				</div>
			</form>
		</div>

	</article>
</body>
</html>