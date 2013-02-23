import distribute_setup
distribute_setup.use_setuptools()
from setuptools import (
    find_packages,
    setup,
)

from os.path import (
    abspath,
    dirname,
    join,
)

VERSION = '0.1.0a01'

PACKAGE_ROOT = abspath(dirname(__file__))

with open(join(PACKAGE_ROOT, 'README.rst')) as f:
    LONG_DESCRIPTION = f.read()

with open(join(PACKAGE_ROOT, 'requirements.txt')) as f:
    REQUIREMENTS = f.read().strip().split('\n')

setup(
    name='interpret',
    version=VERSION,
    url='http://github.com/kentfrazier/interpret',
    author='Kent Frazier',
    description='Simple web service to decompose natural language queries.',
    long_description=LONG_DESCRIPTION,
    keywords='nlp service',
    packages=find_packages(exclude=['.svn', '.git', 'doc', 'test']),
    install_requires=REQUIREMENTS,
)
