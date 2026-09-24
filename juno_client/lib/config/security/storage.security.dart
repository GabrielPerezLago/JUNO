import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class JnTokenStorageRepository {
  final _securityStorage = const FlutterSecureStorage();
  static final String _token = 'token';
  static final String _refreshToken = 'refreshToken';
  JnTokenStorageRepository();

  void saveToken(final String token ) async {
    _securityStorage.write(key: _token, value: token);
  }


  void saveRefreshToken(final String refreshToken) async {
    _securityStorage.write(key: _refreshToken, value: refreshToken);  
  }

  Future<void> removeRefreshToken() async {
    _securityStorage.delete(key: _refreshToken);
  }


  Future<void> removeToken() async {
    _securityStorage.delete(key: _token);
  }

  void reWriteToken(final String token) async {
    await removeToken();
    saveToken(token);
  }


  void reWriteRefreshToken(final String refreshToken) async {
    await removeRefreshToken();
    saveRefreshToken(refreshToken);
  }
  
  Future<String?> get refreshToken async =>  await _securityStorage.read(key: _refreshToken);
}