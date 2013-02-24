import os

import jpype
if jpype.isJVMStarted():
    jpype.shutdownJVM()

from nltk import word_tokenize
from nltk.tag import pos_tag
from nltk.parse import malt

class StanfordParser(object):
    MODEL_ENGLISH_PCFG = 'edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz'

    _JVM_PATH = os.environ.get('JPYPE_JVM_PATH', jpype.getDefaultJVMPath())
    _STANFORD_PARSER_HOME = os.environ.get('STANFORD_PARSER_HOME', '.')
    _JAVA_OPTIONS = (
        '-Djava.class.path=' + os.path.join(_STANFORD_PARSER_HOME, '*'),
        #'-mx150m',
    )
    _OUTPUT_FORMAT = 'penn,typedDependencies'

    def __init__(self, model=MODEL_ENGLISH_PCFG):
        StanfordParser._start_jvm()
        self.model = model
        self._package = jpype.JPackage('edu.stanford.nlp.parser.lexparser')
        self._ParserClass = jpype.JClass('edu.stanford.nlp.parser.lexparser.LexicalizedParser')
        self._parser = self._ParserClass.loadModel(
            model,
            '-outputFormat',
            self._OUTPUT_FORMAT,
        )

    @classmethod
    def _start_jvm(cls):
        if jpype.isJVMStarted():
            return
        jpype.startJVM(cls._JVM_PATH, *cls._JAVA_OPTIONS)


class MaltParser(malt.MaltParser):

    def config_malt(self, bin='maltparser-1.7.2.jar', verbose=False):
        return super(MaltParser, self).config_malt(bin=bin, verbose=verbose)


def parse(sentence):
    pass

def tag(sentence):
    tagged = pos_tag(word_tokenize(sentence))
    return tagged

def test():
    sentence = 'I want to find a mobile developer in austin this month'
    tagged = tag(sentence)
    return tagged
