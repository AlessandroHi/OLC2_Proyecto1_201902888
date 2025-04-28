// Generated from /home/alessandro/Escritorio/Proyecto1_OLC2/OLC2_Proyecto1_201902888/api/grammars/Language.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, EMBEDDED=50, INT=51, BOOL=52, 
		FLOAT=53, STRING=54, RUNE=55, Nil=56, ID=57, WS=58, LINE_COMMENT=59, BLOCK_COMMENT=60;
	public static final int
		RULE_program = 0, RULE_dcl = 1, RULE_varDcl = 2, RULE_matrix = 3, RULE_slice = 4, 
		RULE_funcDcl = 5, RULE_structDcl = 6, RULE_params = 7, RULE_stmt = 8, 
		RULE_cases = 9, RULE_defaultSwitch = 10, RULE_forInit = 11, RULE_expr = 12, 
		RULE_call = 13, RULE_args = 14, RULE_props = 15, RULE_type = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "dcl", "varDcl", "matrix", "slice", "funcDcl", "structDcl", 
			"params", "stmt", "cases", "defaultSwitch", "forInit", "expr", "call", 
			"args", "props", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'var'", "'='", "';'", "':='", "'['", "']'", "'{'", "'}'", "','", 
			"'func'", "'('", "')'", "'type'", "'struct'", "'if'", "'else'", "'switch'", 
			"'for'", "'range'", "'break'", "'continue'", "'return'", "'case'", "':'", 
			"'default'", "'-'", "'*'", "'/'", "'%'", "'+'", "'>'", "'<'", "'>='", 
			"'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'+='", "'-='", "'++'", 
			"'--'", "'.'", "'int'", "'float64'", "'string'", "'bool'", "'rune'", 
			null, null, null, null, null, null, "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "EMBEDDED", "INT", "BOOL", "FLOAT", "STRING", "RUNE", "Nil", 
			"ID", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Language.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<DclContext> dcl() {
			return getRuleContexts(DclContext.class);
		}
		public DclContext dcl(int i) {
			return getRuleContext(DclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 287105026075569314L) != 0)) {
				{
				{
				setState(34);
				dcl();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DclContext extends ParserRuleContext {
		public VarDclContext varDcl() {
			return getRuleContext(VarDclContext.class,0);
		}
		public FuncDclContext funcDcl() {
			return getRuleContext(FuncDclContext.class,0);
		}
		public StructDclContext structDcl() {
			return getRuleContext(StructDclContext.class,0);
		}
		public SliceContext slice() {
			return getRuleContext(SliceContext.class,0);
		}
		public MatrixContext matrix() {
			return getRuleContext(MatrixContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public DclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dcl; }
	}

	public final DclContext dcl() throws RecognitionException {
		DclContext _localctx = new DclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dcl);
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				varDcl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				funcDcl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				structDcl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				slice();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(44);
				matrix();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(45);
				stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDcl; }
	}

	public final VarDclContext varDcl() throws RecognitionException {
		VarDclContext _localctx = new VarDclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDcl);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(T__0);
				setState(49);
				match(ID);
				setState(50);
				type();
				setState(51);
				match(T__1);
				setState(52);
				expr(0);
				setState(54);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(53);
					match(T__2);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				match(T__0);
				setState(57);
				match(ID);
				setState(58);
				type();
				setState(60);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(59);
					match(T__2);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				match(ID);
				setState(63);
				match(T__3);
				setState(64);
				expr(0);
				setState(66);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(65);
					match(T__2);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				match(ID);
				setState(69);
				type();
				setState(71);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(70);
					match(T__2);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatrixContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ArgsContext> args() {
			return getRuleContexts(ArgsContext.class);
		}
		public ArgsContext args(int i) {
			return getRuleContext(ArgsContext.class,i);
		}
		public MatrixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matrix; }
	}

	public final MatrixContext matrix() throws RecognitionException {
		MatrixContext _localctx = new MatrixContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_matrix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(ID);
			setState(76);
			match(T__3);
			setState(77);
			match(T__4);
			setState(78);
			match(T__5);
			setState(79);
			match(T__4);
			setState(80);
			match(T__5);
			setState(81);
			type();
			setState(82);
			match(T__6);
			setState(83);
			match(T__6);
			setState(84);
			args();
			setState(85);
			match(T__7);
			setState(86);
			match(T__8);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(87);
				match(T__6);
				setState(88);
				args();
				setState(89);
				match(T__7);
				setState(90);
				match(T__8);
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			match(T__7);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(98);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SliceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public SliceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slice; }
	}

	public final SliceContext slice() throws RecognitionException {
		SliceContext _localctx = new SliceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_slice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__0);
			setState(102);
			match(ID);
			setState(103);
			match(T__4);
			setState(104);
			match(T__5);
			setState(105);
			type();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(106);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<DclContext> dcl() {
			return getRuleContexts(DclContext.class);
		}
		public DclContext dcl(int i) {
			return getRuleContext(DclContext.class,i);
		}
		public FuncDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDcl; }
	}

	public final FuncDclContext funcDcl() throws RecognitionException {
		FuncDclContext _localctx = new FuncDclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcDcl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__9);
			setState(110);
			match(ID);
			setState(111);
			match(T__10);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(112);
				params();
				}
			}

			setState(115);
			match(T__11);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1090715534753792L) != 0)) {
				{
				setState(116);
				type();
				}
			}

			setState(119);
			match(T__6);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 287105026075569314L) != 0)) {
				{
				{
				setState(120);
				dcl();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(T__7);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(127);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StructDclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public List<VarDclContext> varDcl() {
			return getRuleContexts(VarDclContext.class);
		}
		public VarDclContext varDcl(int i) {
			return getRuleContext(VarDclContext.class,i);
		}
		public StructDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structDcl; }
	}

	public final StructDclContext structDcl() throws RecognitionException {
		StructDclContext _localctx = new StructDclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_structDcl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__12);
			setState(131);
			match(ID);
			setState(132);
			match(T__13);
			setState(133);
			match(T__6);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==ID) {
				{
				{
				setState(134);
				varDcl();
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(LanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LanguageParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(ID);
			setState(143);
			type();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(144);
				match(T__8);
				setState(145);
				match(ID);
				setState(146);
				type();
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStmtContext extends StmtContext {
		public ContinueStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStmtContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CasesContext> cases() {
			return getRuleContexts(CasesContext.class);
		}
		public CasesContext cases(int i) {
			return getRuleContext(CasesContext.class,i);
		}
		public DefaultSwitchContext defaultSwitch() {
			return getRuleContext(DefaultSwitchContext.class,0);
		}
		public SwitchStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public IfStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStmtContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends StmtContext {
		public BreakStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStmtContext extends StmtContext {
		public List<DclContext> dcl() {
			return getRuleContexts(DclContext.class);
		}
		public DclContext dcl(int i) {
			return getRuleContext(DclContext.class,i);
		}
		public BlockStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForCondStmtContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ForCondStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForRangeContext extends StmtContext {
		public List<TerminalNode> ID() { return getTokens(LanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LanguageParser.ID, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ForRangeContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends StmtContext {
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ForStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(StmtContext ctx) { copyFrom(ctx); }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stmt);
		int _la;
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				expr(0);
				}
				break;
			case 2:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(T__6);
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 287105026075569314L) != 0)) {
					{
					{
					setState(154);
					dcl();
					}
					}
					setState(159);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(160);
				match(T__7);
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(161);
				match(T__14);
				setState(162);
				expr(0);
				setState(163);
				stmt();
				setState(166);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(164);
					match(T__15);
					setState(165);
					stmt();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new SwitchStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(168);
				match(T__16);
				setState(169);
				expr(0);
				setState(170);
				match(T__6);
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__22) {
					{
					{
					setState(171);
					cases();
					}
					}
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__24) {
					{
					setState(177);
					defaultSwitch();
					}
				}

				setState(180);
				match(T__7);
				}
				break;
			case 5:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(182);
				match(T__17);
				setState(183);
				forInit();
				setState(184);
				expr(0);
				setState(185);
				match(T__2);
				setState(186);
				expr(0);
				setState(187);
				stmt();
				}
				break;
			case 6:
				_localctx = new ForCondStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(189);
				match(T__17);
				setState(190);
				expr(0);
				setState(191);
				stmt();
				}
				break;
			case 7:
				_localctx = new ForRangeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(193);
				match(T__17);
				setState(194);
				match(ID);
				setState(195);
				match(T__8);
				setState(196);
				match(ID);
				setState(197);
				match(T__3);
				setState(198);
				match(T__18);
				setState(199);
				expr(0);
				setState(200);
				stmt();
				}
				break;
			case 8:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(202);
				match(T__19);
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(203);
					match(T__2);
					}
				}

				}
				break;
			case 9:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(206);
				match(T__20);
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(207);
					match(T__2);
					}
				}

				}
				break;
			case 10:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(210);
				match(T__21);
				setState(212);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(211);
					expr(0);
					}
					break;
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(214);
					match(T__2);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CasesContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public CasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cases; }
	}

	public final CasesContext cases() throws RecognitionException {
		CasesContext _localctx = new CasesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cases);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__22);
			setState(220);
			expr(0);
			setState(221);
			match(T__23);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 287105026075560096L) != 0)) {
				{
				{
				setState(222);
				stmt();
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultSwitchContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public DefaultSwitchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultSwitch; }
	}

	public final DefaultSwitchContext defaultSwitch() throws RecognitionException {
		DefaultSwitchContext _localctx = new DefaultSwitchContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_defaultSwitch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(T__24);
			setState(229);
			match(T__23);
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 287105026075560096L) != 0)) {
				{
				{
				setState(230);
				stmt();
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForInitContext extends ParserRuleContext {
		public VarDclContext varDcl() {
			return getRuleContext(VarDclContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_forInit);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				varDcl();
				setState(237);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				expr(0);
				setState(240);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CalleeContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public CalleeContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SlicesContext extends ExprContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public SlicesContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LogicalContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndexContext extends ExprContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IndexContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(LanguageParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(LanguageParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivModContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivModContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ExprContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public IdentifierContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementContext extends ExprContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public IncrementContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmbeddedContext extends ExprContext {
		public TerminalNode EMBEDDED() { return getToken(LanguageParser.EMBEDDED, 0); }
		public EmbeddedContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualityContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ExprContext {
		public TerminalNode BOOL() { return getToken(LanguageParser.BOOL, 0); }
		public BooleanContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatrixIndexContext extends ExprContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MatrixIndexContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecrementContext extends ExprContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public DecrementContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncDecAssignContext extends ExprContext {
		public Token op;
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IncDecAssignContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InStructContext extends ExprContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public PropsContext props() {
			return getRuleContext(PropsContext.class,0);
		}
		public InStructContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelationalContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatContext extends ExprContext {
		public TerminalNode FLOAT() { return getToken(LanguageParser.FLOAT, 0); }
		public FloatContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegateContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegateContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RuneContext extends ExprContext {
		public TerminalNode RUNE() { return getToken(LanguageParser.RUNE, 0); }
		public RuneContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NillContext extends ExprContext {
		public TerminalNode Nil() { return getToken(LanguageParser.Nil, 0); }
		public NillContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				_localctx = new NegateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(245);
				match(T__25);
				setState(246);
				expr(25);
				}
				break;
			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(247);
				match(T__38);
				setState(248);
				expr(18);
				}
				break;
			case 3:
				{
				_localctx = new IncDecAssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(249);
				match(ID);
				setState(250);
				((IncDecAssignContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
					((IncDecAssignContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(251);
				expr(17);
				}
				break;
			case 4:
				{
				_localctx = new SlicesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(252);
				match(T__4);
				setState(253);
				match(T__5);
				setState(254);
				type();
				setState(255);
				match(T__6);
				setState(256);
				args();
				setState(257);
				match(T__7);
				}
				break;
			case 5:
				{
				_localctx = new IndexContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(259);
				match(ID);
				setState(260);
				match(T__4);
				setState(261);
				expr(0);
				setState(262);
				match(T__5);
				}
				break;
			case 6:
				{
				_localctx = new MatrixIndexContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(264);
				match(ID);
				setState(265);
				match(T__4);
				setState(266);
				expr(0);
				setState(267);
				match(T__5);
				setState(268);
				match(T__4);
				setState(269);
				expr(0);
				setState(270);
				match(T__5);
				}
				break;
			case 7:
				{
				_localctx = new IncrementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(272);
				match(ID);
				setState(273);
				match(T__41);
				}
				break;
			case 8:
				{
				_localctx = new DecrementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(274);
				match(ID);
				setState(275);
				match(T__42);
				}
				break;
			case 9:
				{
				_localctx = new InStructContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(276);
				match(ID);
				setState(277);
				match(T__6);
				setState(278);
				props();
				setState(279);
				match(T__7);
				}
				break;
			case 10:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(281);
				match(BOOL);
				}
				break;
			case 11:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(282);
				match(FLOAT);
				}
				break;
			case 12:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(283);
				match(STRING);
				}
				break;
			case 13:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(284);
				match(INT);
				}
				break;
			case 14:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(285);
				match(ID);
				}
				break;
			case 15:
				{
				_localctx = new NillContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(286);
				match(Nil);
				}
				break;
			case 16:
				{
				_localctx = new RuneContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(287);
				match(RUNE);
				}
				break;
			case 17:
				{
				_localctx = new EmbeddedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(288);
				match(EMBEDDED);
				}
				break;
			case 18:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(289);
				match(T__10);
				setState(290);
				expr(0);
				setState(291);
				match(T__11);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(324);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(322);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(295);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(296);
						((MulDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 939524096L) != 0)) ) {
							((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(297);
						expr(24);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(298);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(299);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__25 || _la==T__29) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(300);
						expr(23);
						}
						break;
					case 3:
						{
						_localctx = new RelationalContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(301);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(302);
						((RelationalContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) ) {
							((RelationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(303);
						expr(22);
						}
						break;
					case 4:
						{
						_localctx = new EqualityContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(304);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(305);
						((EqualityContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__34 || _la==T__35) ) {
							((EqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(306);
						expr(21);
						}
						break;
					case 5:
						{
						_localctx = new LogicalContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(307);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(308);
						((LogicalContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__36 || _la==T__37) ) {
							((LogicalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(309);
						expr(20);
						}
						break;
					case 6:
						{
						_localctx = new CalleeContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(310);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(312); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(311);
								call();
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(314); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 7:
						{
						_localctx = new AssignContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(316);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(317);
						match(T__1);
						setState(318);
						expr(0);
						setState(320);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
						case 1:
							{
							setState(319);
							match(T__2);
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallContext extends ParserRuleContext {
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
	 
		public CallContext() { }
		public void copyFrom(CallContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncCallContext extends CallContext {
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public FuncCallContext(CallContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GetContext extends CallContext {
		public TerminalNode ID() { return getToken(LanguageParser.ID, 0); }
		public GetContext(CallContext ctx) { copyFrom(ctx); }
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_call);
		int _la;
		try {
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				_localctx = new FuncCallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				match(T__10);
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 287105026067793952L) != 0)) {
					{
					setState(328);
					args();
					}
				}

				setState(331);
				match(T__11);
				}
				break;
			case T__43:
				_localctx = new GetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				match(T__43);
				setState(333);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			expr(0);
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(337);
				match(T__8);
				setState(338);
				expr(0);
				}
				}
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(LanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LanguageParser.ID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PropsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_props; }
	}

	public final PropsContext props() throws RecognitionException {
		PropsContext _localctx = new PropsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_props);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(344);
			match(ID);
			setState(345);
			match(T__23);
			setState(346);
			expr(0);
			setState(347);
			match(T__8);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(348);
				match(ID);
				setState(349);
				match(T__23);
				setState(350);
				expr(0);
				setState(351);
				match(T__8);
				}
				}
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1090715534753792L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 23);
		case 1:
			return precpred(_ctx, 22);
		case 2:
			return precpred(_ctx, 21);
		case 3:
			return precpred(_ctx, 20);
		case 4:
			return precpred(_ctx, 19);
		case 5:
			return precpred(_ctx, 24);
		case 6:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001<\u0169\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0005\u0000$\b\u0000\n\u0000\f\u0000"+
		"\'\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001/\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00027\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002=\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002C\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002H\b\u0002\u0003\u0002J\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003]\b"+
		"\u0003\n\u0003\f\u0003`\t\u0003\u0001\u0003\u0001\u0003\u0003\u0003d\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004l\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005r\b\u0005\u0001\u0005\u0001\u0005\u0003\u0005v\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005z\b\u0005\n\u0005\f\u0005}\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u0081\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u0088\b\u0006\n\u0006"+
		"\f\u0006\u008b\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0094\b\u0007\n\u0007"+
		"\f\u0007\u0097\t\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u009c\b\b\n\b\f"+
		"\b\u009f\t\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00a7"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00ad\b\b\n\b\f\b\u00b0\t"+
		"\b\u0001\b\u0003\b\u00b3\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u00cd\b\b\u0001\b\u0001\b\u0003\b\u00d1\b\b\u0001\b\u0001\b"+
		"\u0003\b\u00d5\b\b\u0001\b\u0003\b\u00d8\b\b\u0003\b\u00da\b\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0005\t\u00e0\b\t\n\t\f\t\u00e3\t\t\u0001\n\u0001"+
		"\n\u0001\n\u0005\n\u00e8\b\n\n\n\f\n\u00eb\t\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00f3\b\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0126\b\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0004\f\u0139\b\f\u000b\f\f"+
		"\f\u013a\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0141\b\f\u0005\f\u0143"+
		"\b\f\n\f\f\f\u0146\t\f\u0001\r\u0001\r\u0003\r\u014a\b\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u014f\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005"+
		"\u000e\u0154\b\u000e\n\u000e\f\u000e\u0157\t\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0005\u000f\u0162\b\u000f\n\u000f\f\u000f\u0165\t\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0000\u0001\u0018\u0011\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0000"+
		"\u0007\u0001\u0000()\u0001\u0000\u001b\u001d\u0002\u0000\u001a\u001a\u001e"+
		"\u001e\u0001\u0000\u001f\"\u0001\u0000#$\u0001\u0000%&\u0001\u0000-1\u019f"+
		"\u0000%\u0001\u0000\u0000\u0000\u0002.\u0001\u0000\u0000\u0000\u0004I"+
		"\u0001\u0000\u0000\u0000\u0006K\u0001\u0000\u0000\u0000\be\u0001\u0000"+
		"\u0000\u0000\nm\u0001\u0000\u0000\u0000\f\u0082\u0001\u0000\u0000\u0000"+
		"\u000e\u008e\u0001\u0000\u0000\u0000\u0010\u00d9\u0001\u0000\u0000\u0000"+
		"\u0012\u00db\u0001\u0000\u0000\u0000\u0014\u00e4\u0001\u0000\u0000\u0000"+
		"\u0016\u00f2\u0001\u0000\u0000\u0000\u0018\u0125\u0001\u0000\u0000\u0000"+
		"\u001a\u014e\u0001\u0000\u0000\u0000\u001c\u0150\u0001\u0000\u0000\u0000"+
		"\u001e\u0158\u0001\u0000\u0000\u0000 \u0166\u0001\u0000\u0000\u0000\""+
		"$\u0003\u0002\u0001\u0000#\"\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000"+
		"\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\u0001\u0001"+
		"\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000(/\u0003\u0004\u0002\u0000"+
		")/\u0003\n\u0005\u0000*/\u0003\f\u0006\u0000+/\u0003\b\u0004\u0000,/\u0003"+
		"\u0006\u0003\u0000-/\u0003\u0010\b\u0000.(\u0001\u0000\u0000\u0000.)\u0001"+
		"\u0000\u0000\u0000.*\u0001\u0000\u0000\u0000.+\u0001\u0000\u0000\u0000"+
		".,\u0001\u0000\u0000\u0000.-\u0001\u0000\u0000\u0000/\u0003\u0001\u0000"+
		"\u0000\u000001\u0005\u0001\u0000\u000012\u00059\u0000\u000023\u0003 \u0010"+
		"\u000034\u0005\u0002\u0000\u000046\u0003\u0018\f\u000057\u0005\u0003\u0000"+
		"\u000065\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u00007J\u0001\u0000"+
		"\u0000\u000089\u0005\u0001\u0000\u00009:\u00059\u0000\u0000:<\u0003 \u0010"+
		"\u0000;=\u0005\u0003\u0000\u0000<;\u0001\u0000\u0000\u0000<=\u0001\u0000"+
		"\u0000\u0000=J\u0001\u0000\u0000\u0000>?\u00059\u0000\u0000?@\u0005\u0004"+
		"\u0000\u0000@B\u0003\u0018\f\u0000AC\u0005\u0003\u0000\u0000BA\u0001\u0000"+
		"\u0000\u0000BC\u0001\u0000\u0000\u0000CJ\u0001\u0000\u0000\u0000DE\u0005"+
		"9\u0000\u0000EG\u0003 \u0010\u0000FH\u0005\u0003\u0000\u0000GF\u0001\u0000"+
		"\u0000\u0000GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000I0\u0001"+
		"\u0000\u0000\u0000I8\u0001\u0000\u0000\u0000I>\u0001\u0000\u0000\u0000"+
		"ID\u0001\u0000\u0000\u0000J\u0005\u0001\u0000\u0000\u0000KL\u00059\u0000"+
		"\u0000LM\u0005\u0004\u0000\u0000MN\u0005\u0005\u0000\u0000NO\u0005\u0006"+
		"\u0000\u0000OP\u0005\u0005\u0000\u0000PQ\u0005\u0006\u0000\u0000QR\u0003"+
		" \u0010\u0000RS\u0005\u0007\u0000\u0000ST\u0005\u0007\u0000\u0000TU\u0003"+
		"\u001c\u000e\u0000UV\u0005\b\u0000\u0000V^\u0005\t\u0000\u0000WX\u0005"+
		"\u0007\u0000\u0000XY\u0003\u001c\u000e\u0000YZ\u0005\b\u0000\u0000Z[\u0005"+
		"\t\u0000\u0000[]\u0001\u0000\u0000\u0000\\W\u0001\u0000\u0000\u0000]`"+
		"\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_a\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000ac\u0005\b\u0000"+
		"\u0000bd\u0005\u0003\u0000\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000"+
		"\u0000\u0000d\u0007\u0001\u0000\u0000\u0000ef\u0005\u0001\u0000\u0000"+
		"fg\u00059\u0000\u0000gh\u0005\u0005\u0000\u0000hi\u0005\u0006\u0000\u0000"+
		"ik\u0003 \u0010\u0000jl\u0005\u0003\u0000\u0000kj\u0001\u0000\u0000\u0000"+
		"kl\u0001\u0000\u0000\u0000l\t\u0001\u0000\u0000\u0000mn\u0005\n\u0000"+
		"\u0000no\u00059\u0000\u0000oq\u0005\u000b\u0000\u0000pr\u0003\u000e\u0007"+
		"\u0000qp\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000su\u0005\f\u0000\u0000tv\u0003 \u0010\u0000ut\u0001\u0000"+
		"\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000w{\u0005"+
		"\u0007\u0000\u0000xz\u0003\u0002\u0001\u0000yx\u0001\u0000\u0000\u0000"+
		"z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000"+
		"\u0000|~\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u0080\u0005"+
		"\b\u0000\u0000\u007f\u0081\u0005\u0003\u0000\u0000\u0080\u007f\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u000b\u0001\u0000"+
		"\u0000\u0000\u0082\u0083\u0005\r\u0000\u0000\u0083\u0084\u00059\u0000"+
		"\u0000\u0084\u0085\u0005\u000e\u0000\u0000\u0085\u0089\u0005\u0007\u0000"+
		"\u0000\u0086\u0088\u0003\u0004\u0002\u0000\u0087\u0086\u0001\u0000\u0000"+
		"\u0000\u0088\u008b\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008c\u0001\u0000\u0000"+
		"\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u008d\u0005\b\u0000\u0000"+
		"\u008d\r\u0001\u0000\u0000\u0000\u008e\u008f\u00059\u0000\u0000\u008f"+
		"\u0095\u0003 \u0010\u0000\u0090\u0091\u0005\t\u0000\u0000\u0091\u0092"+
		"\u00059\u0000\u0000\u0092\u0094\u0003 \u0010\u0000\u0093\u0090\u0001\u0000"+
		"\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u000f\u0001\u0000"+
		"\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098\u00da\u0003\u0018"+
		"\f\u0000\u0099\u009d\u0005\u0007\u0000\u0000\u009a\u009c\u0003\u0002\u0001"+
		"\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000"+
		"\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000"+
		"\u0000\u009e\u00a0\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000"+
		"\u0000\u00a0\u00da\u0005\b\u0000\u0000\u00a1\u00a2\u0005\u000f\u0000\u0000"+
		"\u00a2\u00a3\u0003\u0018\f\u0000\u00a3\u00a6\u0003\u0010\b\u0000\u00a4"+
		"\u00a5\u0005\u0010\u0000\u0000\u00a5\u00a7\u0003\u0010\b\u0000\u00a6\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00da"+
		"\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u0011\u0000\u0000\u00a9\u00aa"+
		"\u0003\u0018\f\u0000\u00aa\u00ae\u0005\u0007\u0000\u0000\u00ab\u00ad\u0003"+
		"\u0012\t\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000"+
		"\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000"+
		"\u0000\u0000\u00af\u00b2\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000"+
		"\u0000\u0000\u00b1\u00b3\u0003\u0014\n\u0000\u00b2\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b5\u0005\b\u0000\u0000\u00b5\u00da\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0005\u0012\u0000\u0000\u00b7\u00b8\u0003\u0016\u000b\u0000"+
		"\u00b8\u00b9\u0003\u0018\f\u0000\u00b9\u00ba\u0005\u0003\u0000\u0000\u00ba"+
		"\u00bb\u0003\u0018\f\u0000\u00bb\u00bc\u0003\u0010\b\u0000\u00bc\u00da"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0005\u0012\u0000\u0000\u00be\u00bf"+
		"\u0003\u0018\f\u0000\u00bf\u00c0\u0003\u0010\b\u0000\u00c0\u00da\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c2\u0005\u0012\u0000\u0000\u00c2\u00c3\u0005"+
		"9\u0000\u0000\u00c3\u00c4\u0005\t\u0000\u0000\u00c4\u00c5\u00059\u0000"+
		"\u0000\u00c5\u00c6\u0005\u0004\u0000\u0000\u00c6\u00c7\u0005\u0013\u0000"+
		"\u0000\u00c7\u00c8\u0003\u0018\f\u0000\u00c8\u00c9\u0003\u0010\b\u0000"+
		"\u00c9\u00da\u0001\u0000\u0000\u0000\u00ca\u00cc\u0005\u0014\u0000\u0000"+
		"\u00cb\u00cd\u0005\u0003\u0000\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u00da\u0001\u0000\u0000\u0000"+
		"\u00ce\u00d0\u0005\u0015\u0000\u0000\u00cf\u00d1\u0005\u0003\u0000\u0000"+
		"\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d1\u00da\u0001\u0000\u0000\u0000\u00d2\u00d4\u0005\u0016\u0000\u0000"+
		"\u00d3\u00d5\u0003\u0018\f\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d8\u0005\u0003\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d8\u00da\u0001\u0000\u0000\u0000\u00d9"+
		"\u0098\u0001\u0000\u0000\u0000\u00d9\u0099\u0001\u0000\u0000\u0000\u00d9"+
		"\u00a1\u0001\u0000\u0000\u0000\u00d9\u00a8\u0001\u0000\u0000\u0000\u00d9"+
		"\u00b6\u0001\u0000\u0000\u0000\u00d9\u00bd\u0001\u0000\u0000\u0000\u00d9"+
		"\u00c1\u0001\u0000\u0000\u0000\u00d9\u00ca\u0001\u0000\u0000\u0000\u00d9"+
		"\u00ce\u0001\u0000\u0000\u0000\u00d9\u00d2\u0001\u0000\u0000\u0000\u00da"+
		"\u0011\u0001\u0000\u0000\u0000\u00db\u00dc\u0005\u0017\u0000\u0000\u00dc"+
		"\u00dd\u0003\u0018\f\u0000\u00dd\u00e1\u0005\u0018\u0000\u0000\u00de\u00e0"+
		"\u0003\u0010\b\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00e0\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e2\u0013\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0005\u0019\u0000\u0000\u00e5\u00e9\u0005"+
		"\u0018\u0000\u0000\u00e6\u00e8\u0003\u0010\b\u0000\u00e7\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u0015\u0001\u0000"+
		"\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ed\u0003\u0004"+
		"\u0002\u0000\u00ed\u00ee\u0005\u0003\u0000\u0000\u00ee\u00f3\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f0\u0003\u0018\f\u0000\u00f0\u00f1\u0005\u0003\u0000"+
		"\u0000\u00f1\u00f3\u0001\u0000\u0000\u0000\u00f2\u00ec\u0001\u0000\u0000"+
		"\u0000\u00f2\u00ef\u0001\u0000\u0000\u0000\u00f3\u0017\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f5\u0006\f\uffff\uffff\u0000\u00f5\u00f6\u0005\u001a\u0000"+
		"\u0000\u00f6\u0126\u0003\u0018\f\u0019\u00f7\u00f8\u0005\'\u0000\u0000"+
		"\u00f8\u0126\u0003\u0018\f\u0012\u00f9\u00fa\u00059\u0000\u0000\u00fa"+
		"\u00fb\u0007\u0000\u0000\u0000\u00fb\u0126\u0003\u0018\f\u0011\u00fc\u00fd"+
		"\u0005\u0005\u0000\u0000\u00fd\u00fe\u0005\u0006\u0000\u0000\u00fe\u00ff"+
		"\u0003 \u0010\u0000\u00ff\u0100\u0005\u0007\u0000\u0000\u0100\u0101\u0003"+
		"\u001c\u000e\u0000\u0101\u0102\u0005\b\u0000\u0000\u0102\u0126\u0001\u0000"+
		"\u0000\u0000\u0103\u0104\u00059\u0000\u0000\u0104\u0105\u0005\u0005\u0000"+
		"\u0000\u0105\u0106\u0003\u0018\f\u0000\u0106\u0107\u0005\u0006\u0000\u0000"+
		"\u0107\u0126\u0001\u0000\u0000\u0000\u0108\u0109\u00059\u0000\u0000\u0109"+
		"\u010a\u0005\u0005\u0000\u0000\u010a\u010b\u0003\u0018\f\u0000\u010b\u010c"+
		"\u0005\u0006\u0000\u0000\u010c\u010d\u0005\u0005\u0000\u0000\u010d\u010e"+
		"\u0003\u0018\f\u0000\u010e\u010f\u0005\u0006\u0000\u0000\u010f\u0126\u0001"+
		"\u0000\u0000\u0000\u0110\u0111\u00059\u0000\u0000\u0111\u0126\u0005*\u0000"+
		"\u0000\u0112\u0113\u00059\u0000\u0000\u0113\u0126\u0005+\u0000\u0000\u0114"+
		"\u0115\u00059\u0000\u0000\u0115\u0116\u0005\u0007\u0000\u0000\u0116\u0117"+
		"\u0003\u001e\u000f\u0000\u0117\u0118\u0005\b\u0000\u0000\u0118\u0126\u0001"+
		"\u0000\u0000\u0000\u0119\u0126\u00054\u0000\u0000\u011a\u0126\u00055\u0000"+
		"\u0000\u011b\u0126\u00056\u0000\u0000\u011c\u0126\u00053\u0000\u0000\u011d"+
		"\u0126\u00059\u0000\u0000\u011e\u0126\u00058\u0000\u0000\u011f\u0126\u0005"+
		"7\u0000\u0000\u0120\u0126\u00052\u0000\u0000\u0121\u0122\u0005\u000b\u0000"+
		"\u0000\u0122\u0123\u0003\u0018\f\u0000\u0123\u0124\u0005\f\u0000\u0000"+
		"\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u00f4\u0001\u0000\u0000\u0000"+
		"\u0125\u00f7\u0001\u0000\u0000\u0000\u0125\u00f9\u0001\u0000\u0000\u0000"+
		"\u0125\u00fc\u0001\u0000\u0000\u0000\u0125\u0103\u0001\u0000\u0000\u0000"+
		"\u0125\u0108\u0001\u0000\u0000\u0000\u0125\u0110\u0001\u0000\u0000\u0000"+
		"\u0125\u0112\u0001\u0000\u0000\u0000\u0125\u0114\u0001\u0000\u0000\u0000"+
		"\u0125\u0119\u0001\u0000\u0000\u0000\u0125\u011a\u0001\u0000\u0000\u0000"+
		"\u0125\u011b\u0001\u0000\u0000\u0000\u0125\u011c\u0001\u0000\u0000\u0000"+
		"\u0125\u011d\u0001\u0000\u0000\u0000\u0125\u011e\u0001\u0000\u0000\u0000"+
		"\u0125\u011f\u0001\u0000\u0000\u0000\u0125\u0120\u0001\u0000\u0000\u0000"+
		"\u0125\u0121\u0001\u0000\u0000\u0000\u0126\u0144\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\n\u0017\u0000\u0000\u0128\u0129\u0007\u0001\u0000\u0000\u0129"+
		"\u0143\u0003\u0018\f\u0018\u012a\u012b\n\u0016\u0000\u0000\u012b\u012c"+
		"\u0007\u0002\u0000\u0000\u012c\u0143\u0003\u0018\f\u0017\u012d\u012e\n"+
		"\u0015\u0000\u0000\u012e\u012f\u0007\u0003\u0000\u0000\u012f\u0143\u0003"+
		"\u0018\f\u0016\u0130\u0131\n\u0014\u0000\u0000\u0131\u0132\u0007\u0004"+
		"\u0000\u0000\u0132\u0143\u0003\u0018\f\u0015\u0133\u0134\n\u0013\u0000"+
		"\u0000\u0134\u0135\u0007\u0005\u0000\u0000\u0135\u0143\u0003\u0018\f\u0014"+
		"\u0136\u0138\n\u0018\u0000\u0000\u0137\u0139\u0003\u001a\r\u0000\u0138"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a"+
		"\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b"+
		"\u0143\u0001\u0000\u0000\u0000\u013c\u013d\n\u0010\u0000\u0000\u013d\u013e"+
		"\u0005\u0002\u0000\u0000\u013e\u0140\u0003\u0018\f\u0000\u013f\u0141\u0005"+
		"\u0003\u0000\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0140\u0141\u0001"+
		"\u0000\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0127\u0001"+
		"\u0000\u0000\u0000\u0142\u012a\u0001\u0000\u0000\u0000\u0142\u012d\u0001"+
		"\u0000\u0000\u0000\u0142\u0130\u0001\u0000\u0000\u0000\u0142\u0133\u0001"+
		"\u0000\u0000\u0000\u0142\u0136\u0001\u0000\u0000\u0000\u0142\u013c\u0001"+
		"\u0000\u0000\u0000\u0143\u0146\u0001\u0000\u0000\u0000\u0144\u0142\u0001"+
		"\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145\u0019\u0001"+
		"\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0147\u0149\u0005"+
		"\u000b\u0000\u0000\u0148\u014a\u0003\u001c\u000e\u0000\u0149\u0148\u0001"+
		"\u0000\u0000\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u014a\u014b\u0001"+
		"\u0000\u0000\u0000\u014b\u014f\u0005\f\u0000\u0000\u014c\u014d\u0005,"+
		"\u0000\u0000\u014d\u014f\u00059\u0000\u0000\u014e\u0147\u0001\u0000\u0000"+
		"\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014f\u001b\u0001\u0000\u0000"+
		"\u0000\u0150\u0155\u0003\u0018\f\u0000\u0151\u0152\u0005\t\u0000\u0000"+
		"\u0152\u0154\u0003\u0018\f\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0154"+
		"\u0157\u0001\u0000\u0000\u0000\u0155\u0153\u0001\u0000\u0000\u0000\u0155"+
		"\u0156\u0001\u0000\u0000\u0000\u0156\u001d\u0001\u0000\u0000\u0000\u0157"+
		"\u0155\u0001\u0000\u0000\u0000\u0158\u0159\u00059\u0000\u0000\u0159\u015a"+
		"\u0005\u0018\u0000\u0000\u015a\u015b\u0003\u0018\f\u0000\u015b\u0163\u0005"+
		"\t\u0000\u0000\u015c\u015d\u00059\u0000\u0000\u015d\u015e\u0005\u0018"+
		"\u0000\u0000\u015e\u015f\u0003\u0018\f\u0000\u015f\u0160\u0005\t\u0000"+
		"\u0000\u0160\u0162\u0001\u0000\u0000\u0000\u0161\u015c\u0001\u0000\u0000"+
		"\u0000\u0162\u0165\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000"+
		"\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164\u001f\u0001\u0000\u0000"+
		"\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0166\u0167\u0007\u0006\u0000"+
		"\u0000\u0167!\u0001\u0000\u0000\u0000%%.6<BGI^ckqu{\u0080\u0089\u0095"+
		"\u009d\u00a6\u00ae\u00b2\u00cc\u00d0\u00d4\u00d7\u00d9\u00e1\u00e9\u00f2"+
		"\u0125\u013a\u0140\u0142\u0144\u0149\u014e\u0155\u0163";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}