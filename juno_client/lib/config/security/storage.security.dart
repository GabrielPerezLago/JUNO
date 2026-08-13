import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class JunoTokenStorageRepository {
  final _securityStorage = const FlutterSecureStorage();

  JunoTokenStorageRepository();

  void saveToken(final String token ) async {
    _securityStorage.write(key: 'token', value: token);
  }


  void saveRefreshToken(final String refreshToken) async {
    _securityStorage.write(key: 'refresh token', value: refreshToken);  
  }

  Future<void> removeRefreshToken() async {
    _securityStorage.delete(key: 'refresh token');
  }


  Future<void> removeToken() async {
    _securityStorage.delete(key: 'token');
  }

  void reWriteToken(final String token) async {
    await removeToken();
    saveToken(token);
  }


  void reWriteRefreshToken(final String refreshToken) async {
    await removeRefreshToken();
    saveRefreshToken(refreshToken);
  }

}