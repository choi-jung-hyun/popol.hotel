<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>naverLoginCallback</title>

	<script type="text/javascript">
		var naver_id_login = new naver_id_login("Client ID", "CallBack URL"); // ���� ���������� 'localhost'�� ���Ե� CallBack URL
		
		// ���� ��ū �� ���
		alert(naver_id_login.oauthParams.access_token);
		
		// ���̹� ����� ������ ��ȸ
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		
		// ���̹� ����� ������ ��ȸ ���� ������ ������ ó���� callback function
		function naverSignInCallback() {
			alert(naver_id_login.getProfileData('name'));
			alert(naver_id_login.getProfileData('nickname'));
		}
	</script>
</head>
<body>

</body>
</html>