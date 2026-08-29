import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:juno_client/config/app/app.tools.dart';

abstract interface class JnInputs {}

class JnInput extends StatefulWidget implements JnInputs {
  final TextEditingController controller;
  final bool isOcultable;
  final String lblTextTittle;
  JnInput({
    required this.controller,
    this.isOcultable = false,
    required this.lblTextTittle,
  });
  
  @override
  State<StatefulWidget> createState() => _JnInputState();

}

class _JnInputState extends State<JnInput> {
  TextEditingController get _controller => widget.controller;

  bool _hidden = false;

  
  _JnInputState();

  @override
  void initState()  {
    if (widget.isOcultable) {
      setState(() {
        _hidden = true;
      });
    }
  }

  @override
  Widget build(BuildContext context) => TextField(
    controller: _controller,
    obscureText: _hidden,
    decoration: InputDecoration(
      labelText: widget.lblTextTittle,
      labelStyle: _lblDecoration(),
      enabledBorder: _jnIputBorder(),
      focusedBorder: _jnIputBorder(),

      suffixIcon: _getSuffixIconsPassword(() {
        setState(() {
          _hidden = !_hidden;
        });
      })
    ),
  );




  OutlineInputBorder _jnIputBorder() => OutlineInputBorder(
    borderRadius: BorderRadius.circular(50),
    borderSide: BorderSide(
      width: width(context) * 0.001
    ),
    gapPadding: 20
  );

  Widget? _getSuffixIconsPassword(VoidCallback onClick) => widget.isOcultable ? 
  IconButton(
    onPressed: onClick, 
    icon: Icon(_hidden ? Icons.visibility : Icons.visibility_off)
  ) 
  : null;

  TextStyle _lblDecoration() => TextStyle(
    fontSize: width(context) * _szByPlatform()
  );


  double _szByPlatform() =>  isDesktop ? 0.01 : 0.03;
}


class JnSizedInput extends StatelessWidget implements JnInputs { 
  final double width;
  final double height;
  final TextEditingController controller;
  final String lblTextTittle;
  final bool isOcultable;
 

  JnSizedInput({
    this.width = 0,
    this.height = 0,
    required this.controller,
    required this.lblTextTittle,
    this.isOcultable = false,

  });


  @override
  Widget build(BuildContext context) => SizedBox(
    width: width != 0  ? width : null,
    height: height != 0 ? height : null,
    child: JnInput(
      controller: controller, 
      lblTextTittle: lblTextTittle,
      isOcultable: isOcultable,
    ),
  );
}