import 'package:flutter/material.dart';
import 'package:getwidget/getwidget.dart';
import 'package:go_router/go_router.dart';
import 'package:juno_client/config/app/app.tools.dart';

class ErrorView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          child: Center(
            child: Container(
              alignment: Alignment.center,
              padding: EdgeInsets.symmetric(
                horizontal: width(context) * 0.1,
                vertical: height(context) * 0.15,
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                spacing: height(context) * 0.05,
                children: [
                  Image(
                    image: AssetImage('assets/images/error_logo.png'),
                    width: width(context) * 0.6,
                  ),
                  Text(
                    '404',
                    style: TextStyle(
                      fontSize: width(context) * 0.18,
                      color: junoColorScheme(context).primary,
                    ),
                  ),
                  Center(
                    child: Text(
                      '¡Vaya! , no hay conexion , coprueba tu conexion y intentalo de nuevo',
                      style: TextStyle(
                        fontSize: width(context) * 0.05,
                        color: junoColorScheme(context).error,
                      ),
                      textAlign: TextAlign.center,
                    ),
                  ),
                  GFButton(
                    onPressed: () {
                      context.go('/login');
                    },
                    text: 'Reintentar',
                    color: junoColorScheme(context).secondary,
                    shape: GFButtonShape.pills,
                    size: GFSize.LARGE,
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
