// Compiled by ClojureScript 1.10.844 {:target :nodejs, :nodejs-rt false, :language-out :es5}
goog.provide('beta.core');
goog.require('cljs.core');
goog.require('reagent.core');
beta.core.node$module$react_native = require('react-native');
beta.core.hello = (function beta$core$hello(){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [beta.core.node$module$react_native.view,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"style","style",-496642736),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"flex","flex",-1425124628),(1),new cljs.core.Keyword(null,"align-items","align-items",-267946462),"center",new cljs.core.Keyword(null,"justify-content","justify-content",-1990475787),"center"], null)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [beta.core.node$module$react_native.text,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"style","style",-496642736),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"font-size","font-size",-1847940346),(50)], null)], null),"Hello Krell!"], null)], null);
});
beta.core._main = (function beta$core$_main(var_args){
var args__4777__auto__ = [];
var len__4771__auto___1497 = arguments.length;
var i__4772__auto___1498 = (0);
while(true){
if((i__4772__auto___1498 < len__4771__auto___1497)){
args__4777__auto__.push((arguments[i__4772__auto___1498]));

var G__1499 = (i__4772__auto___1498 + (1));
i__4772__auto___1498 = G__1499;
continue;
} else {
}
break;
}

var argseq__4778__auto__ = ((((0) < args__4777__auto__.length))?(new cljs.core.IndexedSeq(args__4777__auto__.slice((0)),(0),null)):null);
return beta.core._main.cljs$core$IFn$_invoke$arity$variadic(argseq__4778__auto__);
});
goog.exportSymbol('beta.core._main', beta.core._main);

(beta.core._main.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return reagent.core.as_element.call(null,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [beta.core.hello], null));
}));

(beta.core._main.cljs$lang$maxFixedArity = (0));

/** @this {Function} */
(beta.core._main.cljs$lang$applyTo = (function (seq1496){
var self__4759__auto__ = this;
return self__4759__auto__.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq1496));
}));


//# sourceMappingURL=core.js.map
