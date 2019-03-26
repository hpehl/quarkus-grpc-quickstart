// external dependencies
import hljs from 'highlight.js/lib/highlight';
import json from 'highlight.js/lib/languages/json';
import protobuf from  'highlight.js/lib/languages/protobuf';
import 'highlight.js/styles/default.css';
import '@patternfly/patternfly/patternfly.scss';
import '@patternfly/patternfly/patternfly-addons.scss';

// app dependencies
import './favicon.ico';
import './form.js';
import './get-feature.js';
import './list-features.js';
import './result.js';
import './styles.scss';
import './navigation.js';

hljs.registerLanguage('json', json);
hljs.registerLanguage('protobuf', protobuf);