// Compiled by ClojureScript 1.10.844 {:target :nodejs, :nodejs-rt false, :language-out :es5}
goog.provide('reagent.debug');
goog.require('cljs.core');
reagent.debug.has_console = (typeof console !== 'undefined');
reagent.debug.tracking = false;
if((typeof reagent !== 'undefined') && (typeof reagent.debug !== 'undefined') && (typeof reagent.debug.warnings !== 'undefined')){
} else {
reagent.debug.warnings = cljs.core.atom.call(null,null);
}
if((typeof reagent !== 'undefined') && (typeof reagent.debug !== 'undefined') && (typeof reagent.debug.track_console !== 'undefined')){
} else {
reagent.debug.track_console = (function (){var o = ({});
(o.warn = (function() { 
var G__945__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"warn","warn",-436710552)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__945 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__946__i = 0, G__946__a = new Array(arguments.length -  0);
while (G__946__i < G__946__a.length) {G__946__a[G__946__i] = arguments[G__946__i + 0]; ++G__946__i;}
  args = new cljs.core.IndexedSeq(G__946__a,0,null);
} 
return G__945__delegate.call(this,args);};
G__945.cljs$lang$maxFixedArity = 0;
G__945.cljs$lang$applyTo = (function (arglist__947){
var args = cljs.core.seq(arglist__947);
return G__945__delegate(args);
});
G__945.cljs$core$IFn$_invoke$arity$variadic = G__945__delegate;
return G__945;
})()
);

(o.error = (function() { 
var G__948__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"error","error",-978969032)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__948 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__949__i = 0, G__949__a = new Array(arguments.length -  0);
while (G__949__i < G__949__a.length) {G__949__a[G__949__i] = arguments[G__949__i + 0]; ++G__949__i;}
  args = new cljs.core.IndexedSeq(G__949__a,0,null);
} 
return G__948__delegate.call(this,args);};
G__948.cljs$lang$maxFixedArity = 0;
G__948.cljs$lang$applyTo = (function (arglist__950){
var args = cljs.core.seq(arglist__950);
return G__948__delegate(args);
});
G__948.cljs$core$IFn$_invoke$arity$variadic = G__948__delegate;
return G__948;
})()
);

return o;
})();
}
reagent.debug.track_warnings = (function reagent$debug$track_warnings(f){
(reagent.debug.tracking = true);

cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null);

f.call(null);

var warns = cljs.core.deref.call(null,reagent.debug.warnings);
cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null);

(reagent.debug.tracking = false);

return warns;
});

//# sourceMappingURL=debug.js.map
