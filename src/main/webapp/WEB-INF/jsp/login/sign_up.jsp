<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
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
	            //휴대폰번호 입력시 자동 하이픈
	            $('#userPhone').keydown(function (event) {
	            	let lastNum;
	            	var key = event.charCode || event.keyCode || 0;
	             $text = $(this); 
	             if (key !== 8 && key !== 9) {
	                 if ($text.val().length === 3) {
	                     $text.val($text.val() + '-');
	                 }
	                 lastNum =  $text.val().substr(2,1);//휴대폰 앞자리 마지막번호를 걸러냄
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
				 // Key 8번 백스페이스, Key 9번 탭, Key 46번 Delete 부터 0 ~ 9까지, Key 96 ~ 105까지 넘버패트
				 // 한마디로 JQuery 0 ~~~ 9 숫자 백스페이스, 탭, Delete 키 넘버패드외에는 입력못함
	         })
	   });

	});
	//취소버튼 클릭이벤트
	function back() {
		location.href = '/login/loginView.do';
	}
	
	//아이디 중복 확인
	function email_check(){
		
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if($("#userEmail").val() == '' || $("#userEmail").val() == null){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		if(!regExp.test($("#userEmail").val())){//형식이 다른경우
			alert("이메일 형식이 맞지않습니다.");
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
				alert(data.Msg);//중복된아이디
			}else{
				alert(data.Msg);//사용가능
				$("#emailCheck_yn").val("Y");
				
				//사용가능한 경우 수정못하게 버튼 상태변경
				$("#email_chk").hide();
				$("#userEmail").prop("readOnly","true");
				$("#email_modify").show();
			}
		})
		
	}
	//이메일 중복 버튼 이벤트
	function email_modify(){
		
		$("#emailCheck_yn").val("N"); //다시 중복확인해야함
		$("#userEmail").focus();
		$("#userEmail").removeAttr("readOnly");
		$("#email_modify").hide();
		$("#email_chk").show();
	}
	
	//특수문자체크 
	function special_replace(text){
		
		var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
		 
		if(regExp.test(text)){//특수문자 있는경우 false
			return false;
		}
	}
	//이메일 형식체크
	function emailChk(email){
		
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		if(!regExp.test(email)){//형식이 다른경우
			return false;
		}
		
	}
	//비밀번호 정규식 
	function pwChk(pass){
		
		var regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/;

		if(pass == '' || pass == null){
			alert("패스워드를 입력해주세요!");
			return false;
		}
		
		if(!regExp.test(pass)){// 비밀번호 형식에 맞지않는경우
			alert("비밀번호는 8~12자리 특수문자, 숫자, 영문을 조합하여 작성해야됩니다.");
			$("#userPw").val("");
			$("#userPwChk").val("");
			return false;
		}
		pwMatch(pass);//정규식을통과했다면 기존에 입력받앗던 패스워드로 일치체크를함.
	}
	//비밀번호 일치 체크
	function pwMatch(pw){
		
		if(pw != $("#userPwChk").val()){
			alert("패스워드가 일치하지 않습니다.");
			$("#userPwChk").val("");
			return false;
		}
	}
	function signSub(){
		//이름 공백체크
		if($("#userNm").val() == '' || $("#userNm").val() == null){
			alert("이름에 공백을 입력할 수 없습니다.");
			return false;
		}
		//이름에 특수문자 체크
		if(special_replace($("#userNm").val()) == false){
			alert("이름에 특수문자를 사용할 수 없습니다.");
			$("#userNm").val("");
			return false;
		}
		//이메일 형식체크
		if(emailChk($("#userEmail").val()) == false){
			alert("이메일 형식이 적합하지 않습니다.");
			$("#userEmail").val("");
			return false;
		}
		//비밀번호 정규식 및 비밀번호 일치 체크
		if(pwChk($("#userPw").val()) == false){
			return false;
		}
		//휴대폰번호 공백체크
		if(userPhone == '' || userPhone == null){
			alert("휴대폰 번호에 공백을 입력할 수 없습니다.");
			return false;
		}
		//주소체크
		if($("#post_code").val() == '' || $("#post_code").val() == null){
			alert("우편번호를 입력해주세요.");
			return false;
		} 
		if($("#addr").val() == '' || $("#addr").val() == null){
			alert("주소를 입력해주세요.");
			return false;
		}
		if($("#detail_addr").val() == '' || $("#detail_addr").val() == null){
			alert("상세주소를 입력해주세요.");
			return false;
		}
		if(special_replace($("#detail_addr").val()) == false){
			alert("상세주소에 특수문자를 입력할 수 없습니다.");
			return false;
		}
		//약관 체크
		if($("#agree").is(":checked") == false){
			alert("약관동의를 체크해주세요.");
			return false;
		}
		
		if($("#emailCheck_yn").val() != "Y"){
			alert("이메일 중복 체크를 확인해주세요.");
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
			if(data.resultCode > 0){//성공
				alert(data.Msg);
				location.href = "/login/loginView.do";
			}else{					//실패
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
				<h3>회원가입</h3>
			</div>
		</div>
		<div class="col-sm-6 col-md-offset-3">
			<form role="form">
			<input type="hidden" id="emailCheck_yn" value="N">
				<div class="form-group">
					성명 <input type="text" class="form-control" id="userNm" placeholder="이름을 입력해 주세요." maxlength="6">
				</div>
				<div class="form-group">
					이메일 주소 <input type="email" class="form-control" id="userEmail" placeholder="이메일 주소를 입력해주세요." maxlength="30">
							<a href="javascript:email_check();" id="email_chk" class="btn btn-primary"> 중복확인 <i class="fa fa-check spaceLeft"></i></a>
							<a href="javascript:email_modify();" id="email_modify" class="btn btn-primary" style="display:none;"> 수정 <i class="fa fa-check spaceLeft"></i></a>
				</div>
				<div class="form-group">
					비밀번호 <input type="password" class="form-control" id="userPw" placeholder="비밀번호를 입력해주세요." maxlength="12">
				</div>
				<div class="form-group">
					비밀번호 확인<input type="password" class="form-control" id="userPwChk" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요." maxlength="12">
				</div>
				<div class="form-group">
					휴대폰 번호 <input type="text" class="form-control" id="userPhone" placeholder="휴대폰번호를 입력해 주세요."  maxlength="13">
				</div>
				
				주소<br>
				<div class="form-group">
				<input type="text"  id="post_code" placeholder="우편번호" readOnly="readOnly">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
				<div class="form-group" style="margin-top:5px;">
					<input type="text" class="form-control" id="addr" placeholder="주소" readOnly="readOnly">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="detail_addr" placeholder="상세주소">
				</div>

				<div class="form-group">
					<label>약관 동의</label>
					<div data-toggle="buttons">
						<label class="btn btn-primary active"> <span
							class="fa fa-check"></span> <input id="agree" type="checkbox"
							autocomplete="off" checked>
						</label> <a href="#">이용약관</a>에 동의합니다.
					</div>
				</div>

				<div class="form-group text-center">
					<a href="javascript:signSub();" class="btn btn-primary"> 회원가입<i
						class="fa fa-check spaceLeft"></i>
					</a> <a href="javascript:back();" class="btn btn-warning"> 가입취소<i
						class="fa fa-times spaceLeft"></i>
					</a>
				</div>
			</form>
		</div>

	</article>
</body>
</html>