import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';
import 'package:juno_client/domain/entity/SESSION.dart';

class LoginMovileLayout extends StatefulWidget {

    @override
  State<StatefulWidget> createState() => _LoginMovileState();
}

class _LoginMovileState extends State<LoginMovileLayout> {


  @override
  Widget build(BuildContext context) => Scaffold(
    body: SafeArea(
        child: Container(
          alignment: Alignment.center,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Expanded(
                flex: 3,
                child:  Column(
                   mainAxisAlignment: MainAxisAlignment.center,
                   spacing: height(context) * 0.05,
                   children: [
                      Image(
                        image: AssetImage('assets/images/logo.png'),
                        width: width(context) * 0.6,
                      ),
                      Text('JUNO', style: TextStyle(fontSize: width(context) * 0.25, fontFamily: 'Leckerli', color: junoColorScheme(context).secondary ),)
                   ],
                )
              ),
              Expanded(
                flex: 1,
                child: Text('hola'),
              )
            ],
          ),
        ),
    ),
  );
}